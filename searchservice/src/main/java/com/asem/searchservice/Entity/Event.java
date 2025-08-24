package com.asem.searchservice.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "event")
public class Event {
    @Id
    private String id;
    @Field("performer_id")
    private Long performerId;
    private String name;
    private String description;
    private Long total_capacity;
    private Long left_capacity;
    private BigDecimal ticket_price;
    private Long venue_id;
}
