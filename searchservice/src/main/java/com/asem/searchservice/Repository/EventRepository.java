package com.asem.searchservice.Repository;

import com.asem.searchservice.Entity.Event;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventRepository extends ElasticsearchRepository<Event, String> {
    @Query("{ \"bool\": { \"should\": [ " +
            "{ \"fuzzy\": { \"name\": { \"value\": \"?0\", \"fuzziness\": \"AUTO\" } } }, " +
            "{ \"wildcard\": { \"name\": \"?0*\" } } ] } }")
    List<Event> searchByNameFuzzy(String name);

    // Range by ticket price
    @Query("{\"range\": {\"ticket_price\": {\"gte\": ?0, \"lte\": ?1}}}")
    List<Event> findByTicketPriceBetween(double min, double max);
}
