package ENSF480TermProject.backend.models;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "Theatre_Rooms")
public class TheatreRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;

    @ManyToOne
    @JoinColumn(name = "theatre_id", nullable = false)
    @JsonBackReference
    private Theatre theatre;

    @OneToMany(mappedBy = "theatreRoom", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Showtime> showtimes;
    
    // Get and Sets
    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public void setShowtimes(Set<Showtime> showtimes) {
        this.showtimes = showtimes;
    }


    public Theatre getTheatre() {
        return theatre;
    }

    public Set<Showtime> getShowtimes() {
        return showtimes;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

}

