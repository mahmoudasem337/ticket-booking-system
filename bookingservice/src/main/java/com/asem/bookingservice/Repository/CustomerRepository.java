package com.asem.bookingservice.Repository;

import com.asem.bookingservice.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("customerCustomerRepository")
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
