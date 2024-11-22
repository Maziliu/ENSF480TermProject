package ENSF480TermProject.backend.services.search;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ENSF480TermProject.backend.database.MovieRepository;
import ENSF480TermProject.backend.models.Movie;

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
}
