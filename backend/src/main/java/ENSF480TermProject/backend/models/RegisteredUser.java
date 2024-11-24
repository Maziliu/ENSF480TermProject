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

    @Column(name = "is_admin", nullable = false)
    private Boolean isAdmin;

    //CTORS
    public RegisteredUser() {}

    public RegisteredUser(String email, String password){
        this.email = email;
        this.password = password;
        this.isAdmin = false;
    }

    public RegisteredUser(String email, String password, int theatreCredits, String address, boolean isAdmin) {
        this.email = email;
        this.password = password;
        this.theatreCredits = theatreCredits;
        this. address = address;
        this.isAdmin = isAdmin;
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

    public Boolean isAdmin(){
        return isAdmin;
    }

    //Set
}
