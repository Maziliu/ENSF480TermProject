package ENSF480TermProject.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ENSF480TermProject.backend.models.PaymentCard;

public interface PaymentCardRepository extends JpaRepository<PaymentCard, Long> {
    
}
