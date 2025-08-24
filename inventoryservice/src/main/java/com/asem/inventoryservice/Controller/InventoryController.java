package com.asem.inventoryservice.Controller;

import com.asem.inventoryservice.DTO.EventInventoryResponse;
import com.asem.inventoryservice.DTO.VenueInventoryResponse;
import com.asem.inventoryservice.Service.InventoryService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CacheConfig(cacheNames = "inventory")
public class InventoryController {

    private final InventoryService inventoryService;
    public InventoryController( InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/inventory/events")
    public List<EventInventoryResponse> GetAllEvents() {
        return inventoryService.getAllEvents();
    }

    @GetMapping("/inventory/venue/{venueId}")
    public VenueInventoryResponse inventoryByVenueId(@PathVariable("venueId") Long venueId) {
        return inventoryService.getVenueInformation(venueId);
    }

    @GetMapping("/inventory/event/{eventId}")
    public EventInventoryResponse inventoryForEvent(@PathVariable("eventId") Long eventId) {
        return inventoryService.getEventInformation(eventId);
    }

    @PutMapping("/inventory/event/{eventId}/capacity/{capacity}")
    public ResponseEntity<Void> updateEventCapacity(@PathVariable("eventId") Long eventId,
                                                    @PathVariable("capacity") Long ticketsBooked) {
        inventoryService.updateEventCapacity(eventId, ticketsBooked);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/clear")
    public ResponseEntity<String> clearCache() {
        inventoryService.clearAllCaches();
        return ResponseEntity.ok("All caches cleared");
    }
}