package ENSF480TermProject.backend.models;

import java.time.YearMonth;

import jakarta.persistence.Entity;

@Entity
public class DebitCard extends PaymentCard {
    public DebitCard() {}
    public DebitCard(String cardNumber, String cardHolderName, String cvv, YearMonth expireDate) {
        super(cardNumber, cardHolderName, cvv, expireDate);
    }
}
