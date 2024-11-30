package ENSF480TermProject.backend.dtos.user;

import java.util.List;

public class UserDetailsDTO {
    private String first_name, last_name, email, address;
    private List<PaymentCardDTO> paymentCards;

    public UserDetailsDTO() {}

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

    public List<PaymentCardDTO> getPaymentCards() {
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

    public void setPaymentCards(List<PaymentCardDTO> paymentCards) {
        this.paymentCards = paymentCards;
    }

}
