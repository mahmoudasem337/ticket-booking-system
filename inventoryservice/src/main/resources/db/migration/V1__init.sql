-- Create table for Venue
CREATE TABLE venue (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    total_capacity BIGINT NOT NULL
);

-- Create table for Performer
CREATE TABLE performer (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Create table for Event
CREATE TABLE event (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    total_capacity BIGINT NOT NULL,
    left_capacity BIGINT NOT NULL,
    ticket_price DECIMAL(10, 2) NOT NULL,

    venue_id BIGINT NOT NULL,
    performer_id BIGINT NOT NULL,

    CONSTRAINT fk_event_venue FOREIGN KEY (venue_id) REFERENCES venue(id) ON DELETE CASCADE,
    CONSTRAINT fk_event_performer FOREIGN KEY (performer_id) REFERENCES performer(id) ON DELETE CASCADE
);
