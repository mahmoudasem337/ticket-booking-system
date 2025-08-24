package com.asem.bookingservice.DTO;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponse {
    private String event;
    private String description;
    private Long capacity;
    private VenueResponse venue;
    private BigDecimal ticketPrice;
}