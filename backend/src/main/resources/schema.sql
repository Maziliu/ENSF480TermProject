
-- Drop tables in reverse order of dependencies
DROP TABLE IF EXISTS Showtimes;
DROP TABLE IF EXISTS Theatre_Rooms;
DROP TABLE IF EXISTS Theatres;
DROP TABLE IF EXISTS Movies;

-- Create Movies table
CREATE TABLE IF NOT EXISTS Movies (
    movie_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    movie_name VARCHAR(255) NOT NULL UNIQUE,
    duration_in_seconds INT NOT NULL,
    description VARCHAR(255),
    genre VARCHAR(255),
    rating_out_of_ten DOUBLE
);

-- Create Theatres table
CREATE TABLE IF NOT EXISTS Theatres (
    theatre_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    theatre_name VARCHAR(255) NOT NULL UNIQUE,
    address VARCHAR(255) NOT NULL
);

-- Create Theatre_Rooms table
CREATE TABLE IF NOT EXISTS Theatre_Rooms (
    room_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    theatre_id BIGINT NOT NULL,
    FOREIGN KEY (theatre_id) REFERENCES Theatres(theatre_id)
);

-- Create Showtimes table
CREATE TABLE IF NOT EXISTS Showtimes (
    showtime_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    room_id BIGINT NOT NULL,
    movie_id BIGINT NOT NULL,
    air_time DATETIME NOT NULL,
    seat_map JSON NOT NULL,
    FOREIGN KEY (room_id) REFERENCES Theatre_Rooms(room_id),
    FOREIGN KEY (movie_id) REFERENCES Movies(movie_id)
);


