package ENSF480TermProject.backend.dtos.transaction;

import java.math.BigDecimal;

import ENSF480TermProject.backend.models.Purchase;
import ENSF480TermProject.backend.models.RegisteredUser;

public class PaymentResponseDTO extends TransactionResponseDTO{
    public PaymentResponseDTO(Purchase transaction) {
        this.transaction = transaction;
        this.transactionBreakdown = new TransactionBreakdown(transaction.getTransactionAmount(), new BigDecimal(0), transaction.getTransactionAmount());
    }
    public PaymentResponseDTO(Purchase transaction, RegisteredUser registeredUser, BigDecimal amountPaidByCredits, BigDecimal amountPaidByUser) {
        this.transaction = transaction;
        this.registeredUser = registeredUser;
        this.transactionBreakdown = new TransactionBreakdown(transaction.getTransactionAmount(), amountPaidByCredits, amountPaidByUser);
    }

    public PaymentResponseDTO() {
        super();
    }
}
