package ENSF480TermProject.backend.models;


import jakarta.persistence.*;

@Entity
public class TheatreRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "JSON")
    private String seatmap;

    @Column(columnDefinition = "JSON")
    private String movieList;

    @ManyToOne
    @JoinColumn(name = "theatre_id", nullable = false)
    private Theatre theatre;

    // Get and Sets
    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeatmap() {
        return seatmap;
    }

    public void setSeatmap(String seatmap) {
        this.seatmap = seatmap;
    }

    public String getMovieList() {
        return movieList;
    }

    public void setMovieList(String movieList) {
        this.movieList = movieList;
    }
}

