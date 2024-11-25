package ENSF480TermProject.backend.enums;

public enum RefundStatus {
    SUCCESS,
    FAILED_INVALID_TRANSACTION_ID,
    FAILED_PAST_REFUND_PERIOD,
    INTERNAL_ERROR
}
