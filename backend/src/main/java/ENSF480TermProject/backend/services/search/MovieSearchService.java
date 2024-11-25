package ENSF480TermProject.backend.services.search;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ENSF480TermProject.backend.models.Movie;
import ENSF480TermProject.backend.models.Showtime;
import ENSF480TermProject.backend.repositories.MovieRepository;

@Service
public class MovieSearchService {
    private final MovieRepository movieRepository;

    public MovieSearchService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> searchMovie(String movieName){
        return movieRepository.findByMovieNameContainingIgnoreCase(movieName);
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long movieId){
        return movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Movie not found with this id: " + movieId));
    }

    public List<Showtime> getAllShowtimes(){
        return movieRepository.findAllShowtimes();
    }
}
