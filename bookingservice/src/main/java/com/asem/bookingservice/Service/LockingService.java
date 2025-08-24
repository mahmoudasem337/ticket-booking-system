package com.asem.bookingservice.Service;

import com.example.common.*;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class LockingService {

    private final RedissonClient redissonClient;
    private final BookingRepository bookingRepository;
    private final TicketRepository ticketRepository;

    public LockingService(RedissonClient redissonClient, BookingRepository bookingRepository, TicketRepository ticketRepository) {
        this.redissonClient = redissonClient;
        this.bookingRepository = bookingRepository;
        this.ticketRepository = ticketRepository;
    }

    public boolean acquireLock(Booking booking) {
        System.out.println("Trying to acquire lock for ticket " + booking.getTicketEntity().getId());
        RLock lock = redissonClient.getLock("ticket-lock:" + booking.getTicketEntity().getId());
        try {
            boolean locked = lock.tryLock(10, 120, TimeUnit.SECONDS); // wait 10 sec, lease 2 min
            if (locked) {
                System.out.println("LOCK ACQUIRED for ticket " + booking.getTicketEntity().getId());
                return true;
            } else {
                System.out.println("FAILED TO ACQUIRE LOCK for ticket " + booking.getTicketEntity().getId());
                return false;
            }
        } catch (InterruptedException e) {
            System.out.println("ERROR WHILE ACQUIRING LOCK for ticket " + booking.getTicketEntity().getId());
            Thread.currentThread().interrupt();
            return false;
        }
    }

    public void releaseLock(Booking booking) {
        RLock lock = redissonClient.getLock("ticket-lock:" + booking.getTicketEntity().getId());
        if (lock.isHeldByCurrentThread()) {
            lock.unlock();
            System.out.println("LOCK RELEASED for ticket " + booking.getTicketEntity().getId());
        } else {
            System.out.println("LOCK NOT HELD BY CURRENT THREAD for ticket " + booking.getTicketEntity().getId());
        }
    }

    @Scheduled(fixedRate = 30000) // Run every 30 seconds
    public void checkAndHandleExpiredLocks() {
        List<Booking> pendingBookings = bookingRepository.findByStatus(BookingStatus.PENDING);
        for (Booking booking : pendingBookings) {
            RLock lock = redissonClient.getLock("ticket-lock:" + booking.getTicketEntity().getId());
            if (!lock.isLocked()) {
                // Lock has expired or was never acquired
                booking.setStatus(BookingStatus.CANCELLED);
                bookingRepository.save(booking);
                System.out.println("Booking status updated to CANCELLED for booking " + booking.getId());

                Ticket ticket = ticketRepository.findById(booking.getTicketEntity().getId()).orElse(null);
                if (ticket != null && ticket.getStatus().equals(TicketStatus.BOOKED)) {
                    ticket.setStatus(TicketStatus.AVAILABLE);
                    ticketRepository.save(ticket);
                    System.out.println("Ticket status updated to AVAILABLE for ticket " + ticket.getId());
                }
            }
        }
    }
}

