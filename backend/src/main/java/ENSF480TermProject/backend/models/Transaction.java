package ENSF480TermProject.backend.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;

import ENSF480TermProject.backend.enums.TransactionStatus;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "transaction_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "Transactions")
public abstract class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "transaction_id", nullable = false, updatable = false, unique = true)
    protected UUID transactionId;

    @Column(name = "transaction_amount", nullable = false, precision = 10, scale = 2)
    protected BigDecimal transactionAmount;

    @Column(name = "transaction_date_time", nullable = false)
    protected LocalDateTime transactionDateTime;

    @Column(name = "user_id", nullable = true)
    protected Long userId;

    @Column(name = "user_email", nullable = true)
    protected String userEmail;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_status", nullable = false)
    protected TransactionStatus transactionStatus;

    public Transaction(BigDecimal transactionAmount, LocalDateTime transactionDateTime, Long userId, String userEmail) {
        this.transactionAmount = transactionAmount.setScale(2, RoundingMode.HALF_UP);
        this.transactionDateTime = transactionDateTime;
        this.userId = userId;
        this.userEmail = userEmail;
        this.transactionStatus = TransactionStatus.PAID;
    }

    public Transaction() {
    }

    //Get
    public UUID getTransactionId() {
        return transactionId;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    //Set
    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public void setTransactionDateTime(LocalDateTime transactioDateTime) {
        this.transactionDateTime = transactioDateTime;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
