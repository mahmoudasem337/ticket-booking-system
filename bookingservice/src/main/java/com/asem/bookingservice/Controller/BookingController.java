package com.asem.bookingservice.Controller;

import com.asem.bookingservice.DTO.BookingRequest;
import com.asem.bookingservice.DTO.BookingResponse;
import com.asem.bookingservice.Service.BookingService;
import com.example.common.BookingStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/book")
    public BookingResponse createBooking(@RequestBody BookingRequest request) {
        return bookingService.createBooking(request);
    }

    @PutMapping("/book/{id}/status")
    public void updateBookingStatus(
            @PathVariable Long id,
            @RequestParam BookingStatus status) {

        bookingService.changeBookingStatus(id, status);
    }
}