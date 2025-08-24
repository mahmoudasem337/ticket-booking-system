package com.asem.bookingservice.Entity;

import com.asem.bookingservice.Enum.BookingStatus;
import com.example.common.Ticket;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "bookingBookingEntity")
@Table(name = "booking")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long eventId;

    private long customerId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    private Ticket ticketEntity;


    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @PrePersist
    public void prePersist() {
        if (status == null) {
            status = BookingStatus.PENDING;
        }
    }

    private LocalDateTime bookingTime;
}

