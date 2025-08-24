package com.asem.bookingservice.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "CustomerEntity")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String address;
}
