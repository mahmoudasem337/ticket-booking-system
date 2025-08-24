package com.asem.bookingservice.Repository;

import com.asem.bookingservice.*;
import com.asem.bookingservice.Entity.Booking;
import com.asem.bookingservice.Enum.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("bookingBookingRepository")
public interface BookingRepository extends JpaRepository<Booking,Long> {
    List<Booking> findByStatus(BookingStatus status);
}