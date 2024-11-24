package ENSF480TermProject.backend.models;

import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "movie_name", nullable = false, unique = true)
    private String movieName;

    @Column(name = "duration_in_seconds", nullable = false)
    private int durationInSeconds;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "genre", nullable = true)
    private String genre;

    @Column(name = "rating_out_of_ten", nullable = true)
    private Double ratingOutOfTen;

    @ManyToMany(mappedBy = "movies")
    private List<TheatreRoom> theatreRooms;

    //CTORS
    public Movie(){}

    public Movie(String movieName, int durationInSeconds) {
        this.movieName = movieName;
        this.durationInSeconds = durationInSeconds;
    }

    public Movie(String movieName, int durationInSeconds, String description, String genre, Double ratingOutOfTen){
        this.movieName = movieName;
        this.durationInSeconds = durationInSeconds;
        this.description = description;
        this.genre = genre;
        this.ratingOutOfTen = ratingOutOfTen;
    }

    //Get
    public String getDescription() {
        return description;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    public String getGenre() {
        return genre;
    }

    public Long getMovieId() {
        return movieId;
    }
    
    public String getMovieName() {
        return movieName;
    }

    public Double getRatingOutOfTen() {
        return ratingOutOfTen;
    }
    //Set
}
