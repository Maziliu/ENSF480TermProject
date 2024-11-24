package ENSF480TermProject.backend.models;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Theatres")
public class Theatre {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theatre_id")
    private Long theatreId;

    @Column(name = "theatre_name", nullable = false)
    private String theatreName;

    @Column(name = "address", nullable = false, unique = true)
    private String address;

    @OneToMany(mappedBy = "theatre", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<TheatreRoom> theatreRooms;

    // Constructors
    public Theatre() {}

    public Theatre(String theatreName, String address, Set<TheatreRoom> theatreRooms) {
        this.theatreName = theatreName;
        this.address = address;
        this.theatreRooms = theatreRooms;
    }

    // Getters and Setters
    public Long getTheatreId() {
        return theatreId;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<TheatreRoom> getTheatreRooms() {
        return theatreRooms;
    }

    public void setTheatreRooms(Set<TheatreRoom> theatreRooms) {
        this.theatreRooms = theatreRooms;
    }
}

