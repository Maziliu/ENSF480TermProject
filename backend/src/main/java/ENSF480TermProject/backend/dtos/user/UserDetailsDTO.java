package ENSF480TermProject.backend.dtos.user;

import java.util.Set;

import ENSF480TermProject.backend.models.PaymentCard;
import ENSF480TermProject.backend.models.RegisteredUser;

public class UserDetailsDTO {
    private String first_name, last_name, email, address;
    private Set<PaymentCard> paymentCards;

    public UserDetailsDTO() {}
    public UserDetailsDTO(String firstName, String lastName, String address, String email, Set<PaymentCard> paymentCards) {
        this.first_name = firstName;
        this.last_name = lastName;
        this.address = address;
        this.email = email;
        this.paymentCards = paymentCards;
    }

    public UserDetailsDTO(RegisteredUser registeredUser) {
        this.first_name = registeredUser.getFirstName();
        this.last_name = registeredUser.getLastName();
        this.email = registeredUser.getEmail();
        this.address = registeredUser.getAddress();
        this.paymentCards = registeredUser.getPaymentCards();
    }

    //Get
    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public Set<PaymentCard> getPaymentCards() {
        return paymentCards;
    }

    //Set
    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setPaymentCards(Set<PaymentCard> paymentCards) {
        this.paymentCards = paymentCards;
    }

}
