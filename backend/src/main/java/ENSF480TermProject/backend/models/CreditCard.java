package ENSF480TermProject.backend.models;

import java.time.YearMonth;

import jakarta.persistence.Entity;

@Entity
public class CreditCard extends PaymentCard {
    public CreditCard() {}
    public CreditCard(String cardNumber, String cardHolderName, String cvv, YearMonth expireDate) {
        super(cardNumber, cardHolderName, cvv, expireDate);
    }
}
