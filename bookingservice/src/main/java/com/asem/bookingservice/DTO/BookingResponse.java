package com.asem.bookingservice.DTO;

import com.example.common.Ticket;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponse {
    private Long userId;
    private Long eventId;
    private Ticket ticketId;
}
