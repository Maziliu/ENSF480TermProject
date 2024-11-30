package ENSF480TermProject.backend.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    @Column(name = "is_released", nullable = true)
    private Boolean isReleased;

    @Column(name = "release_date", nullable = true)
    private LocalDateTime releaseDate;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Showtime> showtimes;

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

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public Boolean getIsReleased(){
        return this.isReleased;
    }

    //Set
    public void setDescription(String description) {
        this.description = description;
    }

    public void setDurationInSeconds(int durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setRatingOutOfTen(Double ratingOutOfTen) {
        this.ratingOutOfTen = ratingOutOfTen;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setIsReleased(Boolean isReleased) {
        this.isReleased = isReleased;
    }

    public void setShowtimes(List<Showtime> showtimes) {
        this.showtimes = showtimes;
    }
}
