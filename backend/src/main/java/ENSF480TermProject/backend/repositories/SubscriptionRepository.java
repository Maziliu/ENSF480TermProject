package ENSF480TermProject.backend.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ENSF480TermProject.backend.enums.SubscriptionStatus;
import ENSF480TermProject.backend.models.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long>{
    List<Subscription> findBySubscriptionStatusAndIsAutoRenewAndExpiryDateBefore(SubscriptionStatus subscriptionStatus, boolean isAutoRenew, LocalDateTime expiryDate);
}