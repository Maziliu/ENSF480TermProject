package ENSF480TermProject.backend.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;

import ENSF480TermProject.backend.enums.TransactionType;
import ENSF480TermProject.backend.interfaces.TransactionStrategy;
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
import jakarta.persistence.Transient;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "transaction_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "Transactions")
public abstract class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "transaction_id", nullable = false, updatable = false, unique = true)
    private UUID transactionId;

    @Column(name = "transaction_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal transactionAmount;

    @Column(name = "transaction_date_time", nullable = false)
    private LocalDateTime transactionDateTime;

    @Column(name = "user_id", nullable = true)
    private Long userId;

    @Column(name = "user_email", nullable = true)
    private String userEmail;

    @Transient
    private final TransactionStrategy transactionStrategy;

    public Transaction(BigDecimal transactionAmount, LocalDateTime transactionDateTime, TransactionStrategy transactionStrategy, Long userId, String userEmail) {
        this.transactionAmount = transactionAmount.setScale(2, RoundingMode.HALF_UP);
        this.transactionDateTime = transactionDateTime;
        this.transactionStrategy = transactionStrategy;
        this.userId = userId;
        this.userEmail = userEmail;
    }

    public Transaction() {
        this.transactionStrategy = null;
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

    public TransactionStrategy getTransactionStrategy() {
        return transactionStrategy;
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
