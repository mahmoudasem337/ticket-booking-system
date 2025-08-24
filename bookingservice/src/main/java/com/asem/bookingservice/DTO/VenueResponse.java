package com.asem.bookingservice.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VenueResponse {
    private Long id;
    private String name;
    private String address;
    private Long totalCapacity;
}
