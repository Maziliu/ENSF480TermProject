package ENSF480TermProject.backend.models;


import jakarta.persistence.*;

@Entity
public class TheatreRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roomId;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "JSON")
    private String seatmap;

    @Column(columnDefinition = "JSON")
    private String movieList;

    // Get and Sets
    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
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

