package com.example.common;

import com.example.common.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Primary;
import java.util.List;

@Repository
@Primary
public interface BookingRepository extends JpaRepository<Booking,Long> {
    List<Booking> findByStatus(BookingStatus status);

}