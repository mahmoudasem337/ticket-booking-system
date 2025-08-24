package com.asem.bookingservice.Repository;

import com.example.common.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("ticketTicketRepository")
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}