package ENSF480TermProject.backend.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import ENSF480TermProject.backend.interfaces.TransactionStrategy;
import ENSF480TermProject.backend.strategies.RefundTransactionStrategy;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Refund extends Transaction{
    @Column(name = "refunded_transaction_id", nullable = true)
    private UUID refundedTransactionId;

    public Refund() {}

    public Refund(LocalDateTime transactionDateTime, Long userId, String userEmail, UUID refundedTransactionId) {
        super(BigDecimal.valueOf(0), transactionDateTime, userId, userEmail);
        this.refundedTransactionId = refundedTransactionId;
    }

    //Get
    public UUID getRefundedTransactionId() {
        return refundedTransactionId;
    }

    //Set
    public void setRefundedTransactionId(UUID refundedTransactionId) {
        this.refundedTransactionId = refundedTransactionId;
    }
}
