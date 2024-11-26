package ENSF480TermProject.backend.dtos.transaction;

import java.util.UUID;

public class TicketRefundRequestDTO {
    private UUID transactionId;
    private Long userId;
    private String userEmail;

    public TicketRefundRequestDTO() {}

    //Get
    public UUID getTransactionId() {
        return transactionId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Long getUserId() {
        return userId;
    }

    //Set
    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
