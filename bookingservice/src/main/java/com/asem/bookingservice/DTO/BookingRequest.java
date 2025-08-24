package com.asem.bookingservice.DTO;

import com.example.common.Ticket;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingRequest {
    private Long userId;
    private Ticket ticket;
}
