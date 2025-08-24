package com.asem.searchservice.Controller;

import com.asem.searchservice.Entity.Event;
import com.asem.searchservice.Repository.EventRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/search")
public class EventController {

    private final EventRepository repository;
    public EventController(EventRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/fuzzy")
    public List<Event> fuzzySearch(@RequestParam String name) {
        return repository.searchByNameFuzzy(name);
    }

    @GetMapping("/price")
    public List<Event> searchByPrice(@RequestParam double min, @RequestParam double max) {
        return repository.findByTicketPriceBetween(min, max);
    }
}
