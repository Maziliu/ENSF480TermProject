package ENSF480TermProject.backend.dtos.transaction;

import ENSF480TermProject.backend.enums.RefundStatus;
import ENSF480TermProject.backend.models.CreditDiscountCode;
import ENSF480TermProject.backend.models.Refund;

public class RefundResponseDTO extends TransactionResponseDTO {
    private RefundStatus refundStatus;
    private CreditDiscountCode creditDiscountCode;
    private Refund refund;

    public RefundResponseDTO() {
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
