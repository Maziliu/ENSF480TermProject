package payment.Transaction;

import java.sql.Date;

import mvc.models.PaymentCard;
import mvc.models.User;
import payment.Transaction.enums.TransactionType;

public abstract class Transaction {
    protected String transactionReference, userEmail;
    protected Date transactionDate;
    protected PaymentCard userPaymentCard;
    protected double transactionAmount;
    protected TransactionType transactionType;

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPaymentCard(PaymentCard userPaymentCard) {
        this.userPaymentCard = userPaymentCard;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public PaymentCard getUserPaymentCard() {
        return userPaymentCard;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
}
