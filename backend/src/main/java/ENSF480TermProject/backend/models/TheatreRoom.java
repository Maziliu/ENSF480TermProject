package ENSF480TermProject.backend.models;


import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Theatre_Rooms")
public class TheatreRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "seat_map", columnDefinition = "JSON")
    private String seatmap;

    @ManyToOne
    @JoinColumn(name = "theatre_id", nullable = false)
    private Theatre theatre;

    @ManyToMany
    @JoinTable(
        name = "room_movies",
        joinColumns = @JoinColumn(name = "room_id"),
        inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<Movie> movies;

    // Get and Sets
    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getSeatmap() {
        return seatmap;
    }

    public void setSeatmap(String seatmap) {
        this.seatmap = seatmap;
    }

}

