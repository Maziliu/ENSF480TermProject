package ENSF480TermProject.backend.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ENSF480TermProject.backend.services.user.SubscriptionService;

@Component
public class SubscriptionScheduler {
    private final SubscriptionService subscriptionService;

    public SubscriptionScheduler(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService; 
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void scheduleSubcriptionUpdates(){
        subscriptionService.renewAllExpiredSubscriptions();
        subscriptionService.markAllExpiredWithNoAutoRenewSubscriptions();
    }
}
