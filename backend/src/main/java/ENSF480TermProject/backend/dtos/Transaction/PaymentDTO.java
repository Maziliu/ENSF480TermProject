package ENSF480TermProject.backend.dtos.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import ENSF480TermProject.backend.enums.TransactionType;
import ENSF480TermProject.backend.models.Purchase;
import ENSF480TermProject.backend.models.RegisteredUser;
import ENSF480TermProject.backend.models.Ticket;
import ENSF480TermProject.backend.models.Transaction;

public class PaymentDTO extends TransactionDTO{
    public PaymentDTO(Purchase transaction) {
        this.transaction = transaction;
        this.transactionBreakdown = new TransactionBreakdown(transaction.getTransactionAmount(), new BigDecimal(0), transaction.getTransactionAmount());
    }
    public PaymentDTO(Purchase transaction, RegisteredUser registeredUser, BigDecimal amountPaidByCredits, BigDecimal amountPaidByUser) {
        this.transaction = transaction;
        this.registeredUser = registeredUser;
        this.transactionBreakdown = new TransactionBreakdown(transaction.getTransactionAmount(), amountPaidByCredits, amountPaidByUser);
    }

    public PaymentDTO() {
        super();
    }
}
