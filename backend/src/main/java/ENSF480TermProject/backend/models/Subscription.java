package ENSF480TermProject.backend.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import ENSF480TermProject.backend.enums.SubscriptionStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id", nullable = false)
    private Long subscriptionId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private RegisteredUser registeredUser;

    @Enumerated(EnumType.STRING)
    @Column(name = "subscription_status", nullable = false)
    private SubscriptionStatus subscriptionStatus = SubscriptionStatus.INACTIVE;

    @Column(name = "auto_renew", nullable = false)
    private boolean isAutoRenew = true;

    @Column(name = "start_date", nullable = true)
    private LocalDateTime startDate;

    @Column(name = "expiry_date", nullable = true)
    private LocalDateTime expiryDate;

    public Subscription() {}

    public void activateSubscription(){
        subscriptionStatus = SubscriptionStatus.ACTIVE;
        startDate = LocalDateTime.now();
        expiryDate = startDate.plusSeconds(3);
    }

    public void toggleAutoRenew(){
        this.isAutoRenew = (this.isAutoRenew) ? false : true;
    }

    //Get
    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public SubscriptionStatus getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public boolean isAutoRenew(){
        return isAutoRenew;
    }

    public RegisteredUser getRegisteredUser() {
        return registeredUser;
    }

    //Set
    public void setSubscriptionStatus(SubscriptionStatus subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public void setAutoRenew(boolean isAutoRenew) {
        this.isAutoRenew = isAutoRenew;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public void setRegisteredUser(RegisteredUser registeredUser) {
        this.registeredUser = registeredUser;
    }
}
