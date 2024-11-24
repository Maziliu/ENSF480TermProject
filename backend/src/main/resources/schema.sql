DROP TABLE IF EXISTS room_movies;
DROP TABLE IF EXISTS Theatre_Rooms;
DROP TABLE IF EXISTS Theatres;
DROP TABLE IF EXISTS Movies;

CREATE TABLE IF NOT EXISTS Movies (
    movie_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    movie_name VARCHAR(255) NOT NULL UNIQUE,
    duration_in_seconds INT NOT NULL,
    description TEXT,
    genre VARCHAR(255),
    rating_out_of_ten DOUBLE
);

CREATE TABLE IF NOT EXISTS Theatres (
    theatre_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    theatre_name VARCHAR(255) NOT NULL UNIQUE,
    address VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Theatre_Rooms (
    room_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    seat_map JSON,
    theatre_id BIGINT,
    FOREIGN KEY (theatre_id) REFERENCES Theatres(theatre_id)
);

CREATE TABLE IF NOT EXISTS room_movies (
    room_id BIGINT,
    movie_id BIGINT,
    PRIMARY KEY (room_id, movie_id),
    FOREIGN KEY (room_id) REFERENCES Theatre_Rooms(room_id),
    FOREIGN KEY (movie_id) REFERENCES Movies(movie_id)
);
