-- Insert sample data into Movies table
INSERT IGNORE INTO Movies (movie_name, duration_in_seconds, description, genre, rating_out_of_ten) VALUES
('Inception', 8880, 'A thief with the ability to enter people''s dreams takes on a final heist.', 'Sci-Fi, Action', 8.8),
('Titanic', 11760, 'A love story blossoms aboard the Titanic.', 'Romance, Drama', 7.9),
('The Dark Knight', 9120, 'Batman must face his nemesis, the Joker, while protecting Gotham City.', 'Action, Drama', 9.0),
('Interstellar', 10140, 'A team of explorers travel through a wormhole to save humanity.', 'Sci-Fi, Drama', 8.6);

-- Insert sample data into Theatres table
INSERT IGNORE INTO Theatres (theatre_name, address) VALUES
('Downtown Cinema', '123 Main St, Cityville'),
('Grand Central Theatre', '456 Broadway, Metropolis');

-- Insert sample data into Theatre_Rooms table
INSERT IGNORE INTO Theatre_Rooms (theatre_id) VALUES
(1), -- Room 1 in Downtown Cinema
(1), -- Room 2 in Downtown Cinema
(2); -- Room 3 in Grand Central Theatre

-- Insert sample data into Showtimes table
INSERT IGNORE INTO Showtimes (room_id, movie_id, air_time, seat_map) VALUES
(1, 1, '2024-11-23 18:30:00', '[ [false, false, true], [false, true, false] ]'), -- Room 1, Inception, showtime 1
(1, 1, '2024-11-23 21:00:00', '[ [false, false, false], [true, false, true] ]'), -- Room 1, Inception, showtime 2
(2, 2, '2024-11-24 15:00:00', '[ [false, false, false], [false, false, false] ]'), -- Room 2, Titanic, showtime
(3, 3, '2024-11-24 18:00:00', '[ [false, true, false], [false, false, false] ]'); -- Room 3, The Dark Knight, showtime