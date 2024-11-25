package ENSF480TermProject.backend.dtos.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import ENSF480TermProject.backend.enums.RefundStatus;
import ENSF480TermProject.backend.models.CreditDiscountCode;

public class RefundDTO extends TransactionDTO {
    private RefundStatus refundStatus;
    private CreditDiscountCode creditDiscountCode;

    //For Registered Users
    public RefundDTO(RefundStatus refundStatus, LocalDateTime transactionDateTime, BigDecimal refundAmount, Long userId) {
        this.transactionBreakdown = new TransactionBreakdown(refundAmount, refundAmount, new BigDecimal(0));
        this.refundStatus = refundStatus;
    }

    //For no account users
    public RefundDTO(RefundStatus refundStatus, LocalDateTime transactionDateTime, BigDecimal refundAmount, String userEmail, CreditDiscountCode creditDiscountCode){
        this.transactionBreakdown = new TransactionBreakdown(refundAmount, refundAmount.multiply(new BigDecimal(0.85)), new BigDecimal(0));
        this.refundStatus = refundStatus;
        this.creditDiscountCode = creditDiscountCode;
    }

    //Get
    public CreditDiscountCode getCreditDiscountCode() {
        return creditDiscountCode;
    }

    public RefundStatus getRefundStatus() {
        return refundStatus;
    }

    //Set
    public void setCreditDiscountCode(CreditDiscountCode creditDiscountCode) {
        this.creditDiscountCode = creditDiscountCode;
    }

    public void setRefundStatus(RefundStatus refundStatus) {
        this.refundStatus = refundStatus;
    }
}
