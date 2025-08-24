package com.asem.inventoryservice.Service;

import com.asem.inventoryservice.DTO.EventInventoryResponse;
import com.asem.inventoryservice.DTO.VenueInventoryResponse;
import com.asem.inventoryservice.Entity.Event;
import com.asem.inventoryservice.Entity.Venue;
import com.asem.inventoryservice.Repository.EventRepository;
import com.asem.inventoryservice.Repository.VenueRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class InventoryService {
    public final VenueRepository venueRepository;
    public final EventRepository eventRepository;

    public InventoryService(VenueRepository venueRepository, EventRepository eventRepository) {
        this.venueRepository = venueRepository;
        this.eventRepository = eventRepository;
    }

    @Cacheable(value = "eventsList")
    public List<EventInventoryResponse> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(event -> EventInventoryResponse.builder()
                .event(event.getName())
                .description(event.getDescription())
                .capacity(event.getLeftCapacity())
                .venue(event.getVenue())
                .performer(event.getPerformer())
                .ticketPrice(event.getPrice())
                .build()).collect(Collectors.toList());
    }

    public VenueInventoryResponse getVenueInformation(final Long venueId) {
        Venue venue = venueRepository.findById(venueId).orElse(null);
        return VenueInventoryResponse.builder()
                .address(venue.getAddress())
                .venueName(venue.getName())
                .totalCapacity(venue.getTotalCapacity())
                .build();
    }

    @Cacheable(value = "event", key = "#eventId")
    public EventInventoryResponse getEventInformation(final Long eventId) {
        final Event event = eventRepository.findById(eventId).orElse(null);

        return EventInventoryResponse.builder()
                .event(event.getName())
                .description(event.getDescription())
                .capacity(event.getLeftCapacity())
                .venue(event.getVenue())
                .ticketPrice(event.getPrice())
                .performer(event.getPerformer())
                .build();
    }

    public void updateEventCapacity(final Long eventId, final Long ticketsBooked) {
            final Event event = eventRepository.findById(eventId).orElse(null);
            event.setLeftCapacity(event.getLeftCapacity() - ticketsBooked);
            eventRepository.saveAndFlush(event);
            log.info("Updated event capacity for event id: {} with tickets booked: {}", eventId, ticketsBooked);
    }

    @Caching(evict = {
            @CacheEvict(value = "event", allEntries = true),
            @CacheEvict(value = "eventsList", allEntries = true)
    })
    public void clearAllCaches() {
        System.out.println("All caches cleared");
    }
}
