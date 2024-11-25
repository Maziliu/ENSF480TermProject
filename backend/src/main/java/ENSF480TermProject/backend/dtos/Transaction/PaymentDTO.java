package ENSF480TermProject.backend.dtos.Transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;

import ENSF480TermProject.backend.enums.TransactionType;

public class PaymentDTO {
    private UUID transactionId;
    private TransactionType transactionType;
    private LocalDateTime transactionDate;
    private PaymentBreakdown paymentBreakdown;
    private Long userId = null;
    private String userEmail;

    public PaymentDTO(UUID transactionId, TransactionType transactionType, LocalDateTime transactionDate, BigDecimal totalTransactionAmount, Long userId, String userEmail) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.userId = userId;
        this.userEmail = userEmail;

        this.paymentBreakdown = new PaymentBreakdown(totalTransactionAmount, new BigDecimal(0), totalTransactionAmount);
    }
    public PaymentDTO(UUID transactionId, TransactionType transactionType, LocalDateTime transactionDate, BigDecimal totalTransactionAmount, BigDecimal amountPaidByCredits, BigDecimal amountPaidByUser, Long userId, String userEmail) {
         this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.userId = userId;
        this.userEmail = userEmail;

        this.paymentBreakdown = new PaymentBreakdown(totalTransactionAmount, amountPaidByCredits, amountPaidByUser);
    }

    //Get
    public PaymentBreakdown getPaymentBreakdown() {
        return paymentBreakdown;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Long getUserId() {
        return userId;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    //Set
    public void setPaymentBreakdown(PaymentBreakdown paymentBreakdown) {
        this.paymentBreakdown = paymentBreakdown;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public static class PaymentBreakdown {
        private BigDecimal totalAmount, paidByTheatreCredits, paidByUser;

        public PaymentBreakdown(BigDecimal totalAmount, BigDecimal paidByTheatreCredits, BigDecimal paidByUser) {
            this.totalAmount = totalAmount.setScale(2, RoundingMode.HALF_UP);
            this.paidByTheatreCredits = paidByTheatreCredits.setScale(2, RoundingMode.HALF_UP);
            this.paidByUser = paidByUser.setScale(2, RoundingMode.HALF_UP);
        }

        //Get
        public BigDecimal getPaidByTheatreCredits() {
            return paidByTheatreCredits;
        }

        public BigDecimal getPaidByUser() {
            return paidByUser;
        }

        public BigDecimal getTotalAmount() {
            return totalAmount;
        }

        //Set
        public void setPaidByTheatreCredits(BigDecimal paidByTheatreCredits) {
            this.paidByTheatreCredits = paidByTheatreCredits;
        }

        public void setPaidByUser(BigDecimal paidByUser) {
            this.paidByUser = paidByUser;
        }

        public void setTotalAmount(BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
        }
    }
}
