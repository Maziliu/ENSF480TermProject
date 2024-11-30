package ENSF480TermProject.backend.dtos.user;

import java.time.YearMonth;

import ENSF480TermProject.backend.enums.PaymentCardType;

public class PaymentCardDTO {
    private Long cardId;
    private String cardHolderName, cardNumber, cvv;
    private YearMonth expiryDate;
    private PaymentCardType paymentCardType;

    public PaymentCardDTO() {}
    

    //Get
    public String getCardHolderName() {
        return cardHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public YearMonth getExpiryDate() {
        return expiryDate;
    }

    public PaymentCardType getPaymentCardType() {
        return paymentCardType;
    }

    public Long getCardId() {
        return cardId;
    }

    //Set
    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public void setExpiryDate(YearMonth expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setPaymentCardType(PaymentCardType paymentCardType) {
        this.paymentCardType = paymentCardType;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }
}
