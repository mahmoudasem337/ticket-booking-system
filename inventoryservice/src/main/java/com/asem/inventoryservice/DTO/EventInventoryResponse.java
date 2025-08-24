package com.asem.inventoryservice.DTO;

import com.asem.inventoryservice.Entity.Performer;
import com.asem.inventoryservice.Entity.Venue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventInventoryResponse {
    private String event;
    private String description;
    private Long capacity;
    private Venue venue;
    private BigDecimal ticketPrice;
    private Performer performer;
}
