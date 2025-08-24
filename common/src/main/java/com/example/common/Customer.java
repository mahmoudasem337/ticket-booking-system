package com.example.common;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String address;
}
