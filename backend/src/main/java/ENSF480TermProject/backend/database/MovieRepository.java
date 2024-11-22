package ENSF480TermProject.backend.database;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ENSF480TermProject.backend.models.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
    List<Movie> findByMovieNameContainingIgnoreCase(String movieName);
}
