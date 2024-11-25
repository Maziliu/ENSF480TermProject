package ENSF480TermProject.backend.dtos.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import ENSF480TermProject.backend.enums.RefundStatus;
import ENSF480TermProject.backend.models.CreditDiscountCode;
import ENSF480TermProject.backend.models.Refund;

public class RefundDTO extends TransactionDTO {
    private RefundStatus refundStatus;
    private CreditDiscountCode creditDiscountCode;
    private Refund refund;

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

    public RefundDTO() {
        refundStatus = RefundStatus.SUCCESS;
    }

    //Get
    public CreditDiscountCode getCreditDiscountCode() {
        return creditDiscountCode;
    }

    public RefundStatus getRefundStatus() {
        return refundStatus;
    }

    public Refund getRefund() {
        return refund;
    }

    //Set
    public void setCreditDiscountCode(CreditDiscountCode creditDiscountCode) {
        this.creditDiscountCode = creditDiscountCode;
    }

    public void setRefundStatus(RefundStatus refundStatus) {
        this.refundStatus = refundStatus;
    }

    public void setRefund(Refund refund) {
        this.refund = refund;
    }
}
