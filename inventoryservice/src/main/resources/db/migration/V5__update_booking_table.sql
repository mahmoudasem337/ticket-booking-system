ALTER TABLE booking
ADD COLUMN ticket_count BIGINT;

ALTER TABLE booking
ADD COLUMN total_price DECIMAL(19, 2);