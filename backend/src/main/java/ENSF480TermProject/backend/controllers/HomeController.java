package ENSF480TermProject.backend.controllers;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ENSF480TermProject.backend.models.Movie;
import ENSF480TermProject.backend.models.Theatre;
import ENSF480TermProject.backend.services.search.SearchService;

@RestController
@RequestMapping("/api/browse")
@CrossOrigin(origins = "http://localhost:3000")
public class HomeController {
    private final SearchService searchService;

    public HomeController(SearchService searchService){
        this.searchService = searchService;
    }

    @GetMapping("/movies/search")
    public ResponseEntity<List<Movie>> searchMovies(@RequestParam String movieName){
        List<Movie> searchResults = searchService.searchMovie(movieName);

        searchResults.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie left, Movie right){
                return left.getMovieName().compareTo(right.getMovieName());
            }
        });
        return ResponseEntity.ok(searchResults);
    }

    // @GetMapping("/theatres/search")
    // public ResponseEntity<List<Theatre>> searchTheatre(@RequestBody String address){
        
    // }
}
