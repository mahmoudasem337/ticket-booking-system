package com.asem.searchservice.Service;

import com.asem.searchservice.Entity.Event;
import com.asem.searchservice.Repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository repository;
    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public List<Event> fuzzySearch(String name) {
        return repository.searchByNameFuzzy(name);
    }

    public List<Event> searchByPrice(double min, double max) {
        return repository.findByTicketPriceBetween(min, max);
    }

}
