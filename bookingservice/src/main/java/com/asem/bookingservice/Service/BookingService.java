package com.asem.bookingservice.Service;

import com.asem.bookingservice.Client.InventoryClient;
import com.asem.bookingservice.DTO.BookingRequest;
import com.asem.bookingservice.DTO.BookingResponse;
import com.asem.bookingservice.DTO.InventoryResponse;
import com.asem.bookingservice.Entity.Customer;
import com.asem.bookingservice.Repository.CustomerRepository;
import com.asem.bookingservice.Repository.TicketRepository;
import com.example.common.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;
import java.time.LocalDateTime;


@Slf4j
@Service
public class BookingService {
    private final CustomerRepository customerRepository;
    private final InventoryClient inventoryClient;
    private final BookingRepository bookingRepository;
    private final KafkaTemplate<String, Booking> kafkaTemplate;
    private final TicketRepository ticketRepository;
    private final LockingService lockingService;


    public BookingService(CustomerRepository customerRepository, InventoryClient inventoryClient, BookingRepository bookingRepository, KafkaTemplate<String, Booking> kafkaTemplate, TicketRepository ticketRepository, LockingService lockingService) {
        this.customerRepository = customerRepository;
        this.inventoryClient = inventoryClient;
        this.bookingRepository = bookingRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.ticketRepository = ticketRepository;
        this.lockingService = lockingService;
    }

    public BookingResponse createBooking(BookingRequest request) {
        if(TicketStatus.SOLD == request.getTicket().getStatus()) {
            throw new RuntimeException("Sold Ticket");
        }
        try {
            // 1. Check if user exists
            Customer customer = customerRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            // 2. Check inventory
            InventoryResponse inventoryResponse = inventoryClient.getInventory(request.getTicket().getEvent().getId());
            log.info("Inventory Response: {}", inventoryResponse);
            if (inventoryResponse.getCapacity() < 1) {
                throw new RuntimeException("Not enough tickets !!");
            }
            // 3. Create booking
            Ticket ticket = ticketRepository.findById(request.getTicket().getId())
                    .orElseThrow(() -> new RuntimeException("Ticket not found"));
            Booking bookingEvent = createBookingEvent(request, customer, inventoryResponse, ticket);
            lockingService.acquireLock(bookingEvent);
            ticket.setStatus(TicketStatus.BOOKED);
            ticketRepository.saveAndFlush(ticket);
            // 4. Save booking and send Kafka message
            bookingRepository.save(bookingEvent);
            kafkaTemplate.send("Bookings", String.valueOf(request.getTicket().getId()),bookingEvent);
            log.info("Booking sent to Kafka: {}", bookingEvent);
            return BookingResponse.builder()
                    .userId(bookingEvent.getCustomerId())
                    .eventId(bookingEvent.getEventId())
                    .ticketId(bookingEvent.getTicketEntity())
                    .build();
        } catch (Exception e) {
            log.error("Booking failed: {}", e.getMessage(), e);
            throw new RuntimeException("Error while booking: " + e.getMessage(), e);
        }
    }

    public void changeBookingStatus(Long id, BookingStatus newStatus) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found: " + id));

        booking.setStatus(newStatus);
        bookingRepository.save(booking);
    }

    private Booking createBookingEvent(final BookingRequest request,
                                       final Customer customer,
                                       final InventoryResponse inventoryResponse,
                                       final Ticket ticket) {
        Booking booking = new Booking();
        booking.setEventId(request.getTicket().getEvent().getId());
        booking.setCustomerId(customer.getId());
        booking.setBookingTime(LocalDateTime.now());
        booking.setStatus(com.example.common.BookingStatus.PENDING);
        booking.setTicketEntity(ticket);
        return booking;
    }

}
