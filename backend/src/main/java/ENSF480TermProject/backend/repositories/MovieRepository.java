package ENSF480TermProject.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ENSF480TermProject.backend.models.Movie;
import ENSF480TermProject.backend.models.Showtime;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
    List<Movie> findByMovieNameContainingIgnoreCase(String movieName);
    
    @Query("SELECT s FROM Showtime s JOIN s.movie m")
    List<Showtime> findAllShowtimes();
}
