package com.asem.orderservice.Client;

import com.example.common.BookingStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookingClient {

    @Value("${booking.service.url}")
    private String bookingServiceUrl;

    public void updateBookingStatus(Long bookingId, BookingStatus status) {
        RestTemplate restTemplate = new RestTemplate();
        String url = bookingServiceUrl + "/" + bookingId + "/status?status=" + status.name();
        restTemplate.put(url, null);
    }
}
