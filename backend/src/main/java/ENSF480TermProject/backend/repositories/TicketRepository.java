package ENSF480TermProject.backend.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ENSF480TermProject.backend.models.Showtime;
import ENSF480TermProject.backend.models.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{  
    Optional<Ticket> findByCreationDate(LocalDateTime creationDate);

    @Query("SELECT s FROM Showtime s WHERE s.movie.movieId = :movieId AND s.theatreRoom.roomId = :theatreRoomId")
    Optional<Showtime> findByMovieIdAndTheatreRoomId(@Param("movieId") Long movieId, @Param("theatreRoomId") Long theatreRoomId);
} 