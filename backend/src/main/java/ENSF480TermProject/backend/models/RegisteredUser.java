package ENSF480TermProject.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Registered_Users")
public class RegisteredUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "theatre_credits", nullable = false)
    private int theatreCredits = 0;

    @Column(name = "address", nullable = true)
    private String address;

    //CTORS
    public RegisteredUser() {}

    public RegisteredUser(String email, String password){
        this.email = email;
        this.password = password;
    }

    public RegisteredUser(String email, String password, int theatreCredits, String address) {
        this.email = email;
        this.password = password;
        this.theatreCredits = theatreCredits;
        this. address = address;
    }

    //Get
    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getTheatreCredits() {
        return theatreCredits;
    }

    public Long getUserId() {
        return userId;
    }

    //Set
}
