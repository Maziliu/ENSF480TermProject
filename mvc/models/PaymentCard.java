package mvc.models;

import java.sql.Date;

public class PaymentCard {
    String cardNumber, cardHolderName, cvv;
    Date expirationDate;

    public PaymentCard(String cardNumber, String cardHolderName, String cvv, Date expirationDate) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }
    
    public String getCardNumber() {
        return cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }
}
