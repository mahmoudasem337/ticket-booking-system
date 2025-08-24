package com.asem.orderservice.Service;

import java.time.LocalDateTime;

import com.asem.orderservice.Client.BookingClient;
import com.example.common.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.asem.orderservice.Client.InventoryClient;
import com.asem.orderservice.Entity.Order;
import com.asem.orderservice.Repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final BookingClient bookingClient;
    private final TicketRepository ticketRepository;
    private final LockingService lockingService;

    public OrderService(OrderRepository orderRepository, InventoryClient inventoryClient, BookingClient bookingClient, TicketRepository ticketRepository, LockingService lockingService) {
        this.orderRepository = orderRepository;
        this.inventoryClient = inventoryClient;
        this.bookingClient = bookingClient;
        this.ticketRepository = ticketRepository;
        this.lockingService = lockingService;
    }

    @KafkaListener(topics = "Bookings", groupId = "order-service")
    public void orderEvent(Booking bookingEvent) {
        log.info("Received order event: {}", bookingEvent);
        Order order = createOrder(bookingEvent);
        orderRepository.saveAndFlush(order);
        bookingClient.updateBookingStatus(bookingEvent.getId(), BookingStatus.CONFIRMED);
        Ticket ticket = ticketRepository.findById(bookingEvent.getTicketEntity().getId())
                .orElseThrow(() -> new RuntimeException("Booking not found: " +bookingEvent.getTicketEntity().getId()));
        ticket.setStatus(TicketStatus.SOLD);
        ticketRepository.save(ticket);
        // Update Inventory
        inventoryClient.updateInventory(order.getEventId(), 1);
        log.info("Inventory updated for event: {}, less tickets: {}", order.getEventId(),1);
        lockingService.releaseLock(bookingEvent);
    }

    private Order createOrder(Booking bookingEvent) {
        Order order = new Order();
        order.setCustomerId(bookingEvent.getCustomerId());
        order.setEventId(bookingEvent.getEventId());
        order.setPlacedAt(LocalDateTime.now());
        order.setTicketId(bookingEvent.getTicketEntity().getId());
        order.setTotalPrice(bookingEvent.getTicketEntity().getEvent().getPrice());
        return order;
    }

}
