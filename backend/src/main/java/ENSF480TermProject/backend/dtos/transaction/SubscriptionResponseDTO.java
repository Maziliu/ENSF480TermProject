package ENSF480TermProject.backend.dtos.transaction;

import java.math.BigDecimal;

import ENSF480TermProject.backend.models.RegisteredUser;
import ENSF480TermProject.backend.models.Subscription;
import ENSF480TermProject.backend.models.SubscriptionRenewal;

public class SubscriptionResponseDTO extends TransactionResponseDTO {
    public SubscriptionResponseDTO() {}

    public SubscriptionResponseDTO(SubscriptionRenewal subscriptionTransaction, RegisteredUser registeredUser) {
        this.transaction = subscriptionTransaction;
        this.transactionBreakdown = new TransactionBreakdown(BigDecimal.valueOf(20), BigDecimal.valueOf(0), BigDecimal.valueOf(20));
        this.registeredUser = registeredUser;
    }
}
