package com.asem.bookingservice.Client;

import com.asem.bookingservice.DTO.InventoryResponse;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class InventoryClient {

    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;

    public InventoryResponse getInventory(final Long eventId) {
        final RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(inventoryServiceUrl + "/event/" + eventId, InventoryResponse.class);
    }
}


