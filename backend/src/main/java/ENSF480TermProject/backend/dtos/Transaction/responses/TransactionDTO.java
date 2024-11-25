package ENSF480TermProject.backend.dtos.Transaction.responses;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;

import ENSF480TermProject.backend.enums.TransactionType;
import ENSF480TermProject.backend.models.RegisteredUser;
import ENSF480TermProject.backend.models.Transaction;

public class TransactionDTO {
    protected Transaction transaction;
    // protected Long userId;
    // protected String userEmail;
    // protected TransactionType transactionType;
    protected TransactionBreakdown transactionBreakdown;
    protected RegisteredUser registeredUser;
    // protected UUID transactionId;
    // protected LocalDateTime transactionDateTime;

    //Get
    public TransactionBreakdown getTransactionBreakdown() {
        return transactionBreakdown;
    }

    // public TransactionType getTransactionType() {
    //     return transactionType;
    // }

    // public String getUserEmail() {
    //     return userEmail;
    // }

    // public Long getUserId() {
    //     return userId;
    // }

    // public LocalDateTime getTransactionDateTime() {
    //     return transactionDateTime;
    // }

    // public UUID getTransactionId() {
    //     return transactionId;
    // }

    public Transaction getTransaction() {
        return transaction;
    }

    public RegisteredUser getRegisteredUser() {
        return registeredUser;
    }

    //Set
    public void setTransactionBreakdown(TransactionBreakdown transactionBreakdown) {
        this.transactionBreakdown = transactionBreakdown;
    }

    public void setRegisteredUser(RegisteredUser registeredUser) {
        this.registeredUser = registeredUser;
    }

    // public void setTransactionType(TransactionType transactionType) {
    //     this.transactionType = transactionType;
    // }

    // public void setUserEmail(String userEmail) {
    //     this.userEmail = userEmail;
    // }

    // public void setUserId(Long userId) {
    //     this.userId = userId;
    // }
    
    // public void setTransactionDateTime(LocalDateTime transactionDateTime) {
    //     this.transactionDateTime = transactionDateTime;
    // }

    // public void setTransactionId(UUID transactionId) {
    //     this.transactionId = transactionId;
    // }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
    
    public static class TransactionBreakdown {
        private BigDecimal totalAmount, inTheatreCredits, inUserBalance;

        public TransactionBreakdown(BigDecimal totalAmount, BigDecimal inTheatreCredits, BigDecimal inUserBalance) {
            this.totalAmount = totalAmount.setScale(2, RoundingMode.HALF_UP);
            this.inTheatreCredits = inTheatreCredits.setScale(2, RoundingMode.HALF_UP);
            this.inUserBalance = inUserBalance.setScale(2, RoundingMode.HALF_UP);
        }

        //Get
        public BigDecimal getInUserBalance() {
            return inUserBalance;
        }

        public BigDecimal getInTheatreCredits() {
            return inTheatreCredits;
        }

        public BigDecimal getTotalAmount() {
            return totalAmount;
        }

        //Set
        public void setInUserBalance(BigDecimal inUserBalance) {
            this.inUserBalance = inUserBalance;
        }

        public void setInTheatreCredits(BigDecimal inTheatreCredits) {
            this.inTheatreCredits = inTheatreCredits;
        }

        public void setTotalAmount(BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
        }
        
}
}
