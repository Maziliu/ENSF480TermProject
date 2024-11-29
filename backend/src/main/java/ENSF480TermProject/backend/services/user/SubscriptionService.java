package ENSF480TermProject.backend.services.user;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ENSF480TermProject.backend.enums.SubscriptionStatus;
import ENSF480TermProject.backend.models.RegisteredUser;
import ENSF480TermProject.backend.models.Subscription;
import ENSF480TermProject.backend.repositories.RegisteredUserRepository;
import ENSF480TermProject.backend.repositories.SubscriptionRepository;
import ENSF480TermProject.backend.services.payment.TransactionService;

@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final RegisteredUserRepository registeredUserRepository;
    private final TransactionService transactionService;

    public SubscriptionService(SubscriptionRepository subscriptionRepository, RegisteredUserRepository registeredUserRepository, TransactionService transactionService) {
        this.subscriptionRepository = subscriptionRepository;
        this.registeredUserRepository = registeredUserRepository;
        this.transactionService = transactionService;
    }

    public List<Subscription> findSubscriptionsForRenewal() {
        LocalDateTime now = LocalDateTime.now();
        return subscriptionRepository.findByStatusAndAutoRenewAndExpiryDateBefore(SubscriptionStatus.ACTIVE, true, now);
    }

    public List<Subscription> findSubscriptionsToMarkExpired() {
        LocalDateTime now = LocalDateTime.now();
        return subscriptionRepository.findByStatusAndAutoRenewAndExpiryDateBefore(SubscriptionStatus.ACTIVE, false, now);
    }

    public void markAllExpiredWithNoAutoRenewSubscriptions(){
        List<Subscription> unMarkedExpiredSubscriptions = findSubscriptionsToMarkExpired();

        for(Subscription subscription : unMarkedExpiredSubscriptions){
            markSubcriptionAsExpired(subscription);
            subscriptionRepository.save(subscription);
        }
    }

    public void renewAllExpiredSubscriptions() {
        List<Subscription> subscriptionsToRenew = findSubscriptionsForRenewal();

        for (Subscription subscription : subscriptionsToRenew) {
            renewSubscription(subscription);
            subscriptionRepository.save(subscription);
            //MAKE SURTE TO MAKE TEH TRANSACTION
        }
    }

    private void renewSubscription(Subscription subscription){
        SubscriptionStatus status = subscription.getSubscriptionStatus();
        LocalDateTime newExpireDate = null;

        if(status == SubscriptionStatus.ACTIVE){
            newExpireDate = subscription.getExpiryDate().plusYears(1);
        }
        else if (status == SubscriptionStatus.EXPIRED) {
            newExpireDate = LocalDateTime.now().plusYears(1);
            subscription.setSubscriptionStatus(SubscriptionStatus.ACTIVE);
        }

        subscription.setExpiryDate(newExpireDate);
    }

    private void markSubcriptionAsExpired(Subscription subscription){
        subscription.setSubscriptionStatus(SubscriptionStatus.EXPIRED);
    }

    public Optional<Subscription> renewOrExtendSubscriptionManually(Long userId){
        Optional<RegisteredUser> user = registeredUserRepository.findById(userId);

        if(user.isEmpty()){
            //Expection
        }

        Subscription subscription = user.get().getSubscription();
        renewSubscription(subscription);

        return Optional.of(subscriptionRepository.save(subscription));
    }
}
