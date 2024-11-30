package ENSF480TermProject.backend.models;

import java.time.Year;
import java.time.YearMonth;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import ENSF480TermProject.backend.enums.PaymentCardType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "card_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "Payment_Cards")
public class PaymentCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long cardId;

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "card_holder_name", nullable = false)
    private String cardHolderName;

    @Column(name = "cvv", nullable = false)
    private String cvv; 

    @Column(name = "expire_date")
    private YearMonth expireDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    RegisteredUser registeredUser;

    public PaymentCard() {}

    public PaymentCard(String cardNumber, String cardHolderName, String cvv, YearMonth expireDate) {
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expireDate = expireDate;
    }

    //Get
    public String getCardHolderName() {
        return cardHolderName;
    }

    public Long getCardId() {
        return cardId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public YearMonth getExpireDate() {
        return expireDate;
    }

    public RegisteredUser getRegisteredUser() {
        return registeredUser;
    }

    //Set
    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public void setExpireDate(YearMonth expireDate) {
        this.expireDate = expireDate;
    }

    public void setRegisteredUser(RegisteredUser registeredUser) {
        this.registeredUser = registeredUser;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) { return true; }
        if(obj == null || getClass() != obj.getClass()) { return false; }
        return ((PaymentCard) obj).cardId == this.cardId;
    }
}
