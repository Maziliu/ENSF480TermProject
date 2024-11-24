-- Insert into Movies
INSERT IGNORE INTO Movies (movie_name, duration_in_seconds, description, genre, rating_out_of_ten) VALUES
('Inception', 8880, 'A thief with the ability to enter people''s dreams takes on a final heist.', 'Sci-Fi, Action', 8.8),
('The Dark Knight', 9120, 'Batman must face his nemesis, the Joker, while protecting Gotham City.', 'Action, Drama', 9.0),
('Titanic', 11760, 'A love story blossoms aboard the ill-fated RMS Titanic.', 'Romance, Drama', 7.9),
('The Shawshank Redemption', 8520, 'Two imprisoned men bond over years and find solace and eventual redemption.', 'Drama', 9.3),
('Interstellar', 10140, 'A team of explorers travel through a wormhole to save humanity.', 'Sci-Fi, Drama', 8.6),
('Parasite', 7920, 'A poor family schemes to become employed by a wealthy family.', 'Thriller, Drama', 8.6),
('The Godfather', 10560, 'The aging patriarch of a crime dynasty transfers control to his son.', 'Crime, Drama', 9.2),
('Avengers: Endgame', 10800, 'The Avengers assemble one final time to undo Thanos'' actions.', 'Action, Sci-Fi', 8.4),
('Forrest Gump', 8520, 'The story of a man with a kind heart and an extraordinary life.', 'Drama, Romance', 8.8),
('Joker', 7320, 'The origin story of Gotham City''s most notorious villain.', 'Thriller, Drama', 8.5);

-- Insert into Theatres
INSERT IGNORE INTO Theatres (theatre_name, address) VALUES
('Downtown Cinema', '123 Main St, Cityville'),
('Grand Central Theatre', '456 Broadway, Metropolis');

-- Insert into Theatre_Rooms
INSERT IGNORE INTO Theatre_Rooms (seat_map, theatre_id) VALUES
('{"rows":10,"columns":20}', 1),
('{"rows":8,"columns":15}', 1),
('{"rows":12,"columns":25}', 2);

-- Insert into Room_Movies (Mapping Between Movies and Theatre_Rooms)
INSERT IGNORE INTO room_movies (room_id, movie_id) VALUES
(1, 1), -- Room 1 -> Inception
(1, 2), -- Room 1 -> The Dark Knight
(2, 3), -- Room 2 -> Titanic
(2, 4), -- Room 2 -> The Shawshank Redemption
(3, 5), -- Room 3 -> Interstellar
(3, 6), -- Room 3 -> Parasite
(3, 7); -- Room 3 -> The Godfather;
