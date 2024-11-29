package ENSF480TermProject.backend.dtos.transaction;

import ENSF480TermProject.backend.models.Subscription;

public class SubscriptionResponseDTO extends TransactionResponseDTO {
    private Subscription subscription;

    public SubscriptionResponseDTO() {}

    public SubscriptionResponseDTO(Subscription subscription) {
        this.subscription = subscription;
    }

    //Get
    public Subscription getSubscription() {
        return subscription;
    }

    //Set
    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
}
