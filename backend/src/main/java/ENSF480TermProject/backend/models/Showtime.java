package ENSF480TermProject.backend.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Showtimes")
public class Showtime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "showtime_id")
    private Long showtimeId;

    @Column(name = "air_time", nullable = false)
    private LocalDateTime airTime;

    @Column(name = "seat_map", nullable = false, columnDefinition = "JSON")
    private String seatMap;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    @JsonBackReference
    private TheatreRoom theatreRoom;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    // Getters and Setters
    public LocalDateTime getAirTime() {
        return airTime;
    }
    
    public Movie getMovie() {
        return movie;
    }
    
    public String getSeatMap() {
        return seatMap;
    }

    public Long getShowtimeId() {
        return showtimeId;
    }

    public TheatreRoom getTheatreRoom() {
        return theatreRoom;
    }

    public void setAirTime(LocalDateTime airTime) {
        this.airTime = airTime;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setSeatMap(String seatMap) {
        this.seatMap = seatMap;
    }

    public void setShowtimeId(Long showtimeId) {
        this.showtimeId = showtimeId;
    }

    public void setTheatreRoom(TheatreRoom theatreRoom) {
        this.theatreRoom = theatreRoom;
    }
}

