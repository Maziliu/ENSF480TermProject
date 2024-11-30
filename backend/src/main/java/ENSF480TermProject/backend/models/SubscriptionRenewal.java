package ENSF480TermProject.backend.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.util.ClassUtil.Ctor;

import jakarta.persistence.Entity;

@Entity
public class SubscriptionRenewal extends Transaction {
    public SubscriptionRenewal() {}
    
    public SubscriptionRenewal(Long userId, String userEmail) {
        super(BigDecimal.valueOf(20), LocalDateTime.now(), userId, userEmail);
    }

}
