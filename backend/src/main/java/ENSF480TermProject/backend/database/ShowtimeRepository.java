package ENSF480TermProject.backend.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ENSF480TermProject.backend.models.Showtime;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
    
    @Query("SELECT s FROM Showtime s WHERE s.movie.movieId = :movieId")
    List<Showtime> findByMovieId(@Param("movieId") Long movieId);

    @Query("SELECT s FROM Showtime s WHERE s.theatreRoom.roomId = :roomId")
    List<Showtime> findByTheatreRoomId(@Param("roomId") Long roomId);

    @Query("SELECT s FROM Showtime s WHERE s.theatreRoom.theatre.theatreId = :theatreId")
    List<Showtime> findByTheatreId(@Param("theatreId") Long theatreId);

    @Query("SELECT s FROM Showtime s WHERE s.showtimeId = :showtimeId")
    Showtime findByShowtimeId(@Param("showtimeId") Long showtimeId);
}

