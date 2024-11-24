package ENSF480TermProject.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ENSF480TermProject.backend.models.Movie;
import ENSF480TermProject.backend.services.search.MovieSearchService;

@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "http://localhost:3000")
public class MovieController {
    private final MovieSearchService movieSearchService;

    public MovieController(MovieSearchService movieSearchService) {
        this.movieSearchService = movieSearchService;
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long movieId){
        return ResponseEntity.ok(movieSearchService.getMovieById(movieId));
    }
}
