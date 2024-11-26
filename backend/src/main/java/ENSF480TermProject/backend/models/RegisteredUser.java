package ENSF480TermProject.backend.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "theatre_credits", nullable = false)
    private int theatreCredits = 0;

    @Column(name = "address", nullable = true)
    private String address;

    @Column(name = "first_name", nullable = true)
    private String firstName;

    @Column(name = "last_name", nullable = true)
    private String lastName;

    @JsonIgnore
    @Column(name = "is_admin", nullable = false)
    private Boolean isAdmin;

    @OneToMany(mappedBy = "registeredUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<PaymentCard> paymentCards;

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

    public void addPaymentCard(PaymentCard paymentCard){
        this.paymentCards.add(paymentCard);
    }

    public void removePaymentCard(PaymentCard paymentCard){
        this.paymentCards.remove(paymentCard);
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

    public Set<PaymentCard> getPaymentCards() {
        return paymentCards;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    //Set
    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPaymentCards(Set<PaymentCard> paymentCards) {
        this.paymentCards = paymentCards;
    }

    public void setTheatreCredits(int theatreCredits) {
        this.theatreCredits = theatreCredits;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
