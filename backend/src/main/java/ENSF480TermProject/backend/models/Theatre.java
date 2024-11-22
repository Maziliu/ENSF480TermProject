package ENSF480TermProject.backend.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "theatre_id")
    private List<TheatreRoom> theatreRooms;

    //CTORS
    public Theatre() {}

    public Theatre(String theatreName, String address, List<TheatreRoom> theatreRooms) {
        this.theatreName = theatreName;
        this.address = address;
        this.theatreRooms = theatreRooms;
    }

     // Getters and Setters
    public String getName() {
        return theatreName;
    }

    public void setName(String theatreName) {
        this.theatreName = theatreName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<TheatreRoom> getRooms() {
        return theatreRooms;
    }

    public void setRooms(List<TheatreRoom> theatreRooms) {
        this.theatreRooms = theatreRooms;
    }
}
