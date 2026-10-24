-- =============================
-- Trip Day Description Schema
-- =============================

-- Drop tables if they exist
DROP TABLE IF EXISTS day_description;
DROP TABLE IF EXISTS trip;

-- =============================
-- Trip Table
-- =============================
CREATE TABLE trip (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    trip_name VARCHAR(255) NOT NULL
);

-- =============================
-- Day Description Table
-- =============================
CREATE TABLE day_description (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(70) NOT NULL,
    description VARCHAR(800) NOT NULL,
    location VARCHAR(255),
    time VARCHAR(50),
    packing_suggestions VARCHAR(800),
    save_in_library TINYINT(1) DEFAULT 0,
    deleted TINYINT(1) DEFAULT 0,
    trip_id BIGINT,
    CONSTRAINT fk_trip
        FOREIGN KEY (trip_id)
        REFERENCES trip(id)
        ON DELETE CASCADE
);

-- =============================
-- Sample Data
-- =============================

-- Sample Trip
INSERT INTO trip (trip_name) VALUES
('Goa Vacation'),
('Kerala Backwaters');

-- Sample Day Descriptions
INSERT INTO day_description (title, description, location, time, packing_suggestions, save_in_library, trip_id)
VALUES
('Dinner Under the Stars', 'Romantic candlelight dinner with live music', 'Calangute Beach', 'Evening', 'Smart casual wear', 1, 1),
('Backwater Cruise', 'Relaxing houseboat cruise through Kerala backwaters', 'Alleppey', 'Morning', 'Comfortable clothes', 1, 2);
