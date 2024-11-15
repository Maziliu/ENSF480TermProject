package payment.Transaction;

import mvc.models.PaymentCard;
import mvc.models.Ticket;
import payment.Transaction.enums.TransactionType;

public class Purchase extends Transaction {
    Ticket ticketItem;

    public Purchase(String userEmail, PaymentCard userPaymentCard, Ticket ticketItem, double transactionAmount) {
        this.userEmail = userEmail;
        this.userPaymentCard = userPaymentCard;
        this.ticketItem = ticketItem;
        this.transactionAmount = transactionAmount;
        this.transactionType = TransactionType.PURCHASE;
    }
    
    @Override
    public String toString() {
        //TODO: Implement logging
        return "";
    }
}
