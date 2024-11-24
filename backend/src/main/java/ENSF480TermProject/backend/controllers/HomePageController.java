package ENSF480TermProject.backend.controllers;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ENSF480TermProject.backend.models.Movie;
import ENSF480TermProject.backend.models.Theatre;
import ENSF480TermProject.backend.services.search.MovieSearchService;
import ENSF480TermProject.backend.services.search.TheatreSearchService;

@RestController
@RequestMapping("/browse")
@CrossOrigin(origins = "http://localhost:3000")
public class HomePageController {
    private final MovieSearchService movieSearchService;
    private final TheatreSearchService theatreSearchService;

    public HomePageController(MovieSearchService movieSearchService, TheatreSearchService theatreSearchService){
        this.movieSearchService = movieSearchService;
        this.theatreSearchService = theatreSearchService;
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies(){
        return ResponseEntity.ok(movieSearchService.getAllMovies());
    }

    @GetMapping("/movies/search")
    public ResponseEntity<List<Movie>> searchMovies(@RequestParam String movieName){
        List<Movie> searchResults = movieSearchService.searchMovie(movieName);

        searchResults.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie left, Movie right){
                return left.getMovieName().compareTo(right.getMovieName());
            }
        });
        return ResponseEntity.ok(searchResults);
    }

    @GetMapping("/theatres")
    public ResponseEntity<List<Theatre>> getAllTheatres(){
       return ResponseEntity.ok(theatreSearchService.getAllTheatres());
    }

    @GetMapping("/theatres/search")
    public ResponseEntity<List<Movie>> getMoviesAtSpecificTheatre(String theatreName){
        List<Movie> results = theatreSearchService.getAllMoviesAtSpecificTheatre(theatreName);
        if(results.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(theatreSearchService.getAllMoviesAtSpecificTheatre(theatreName));
    }
}
