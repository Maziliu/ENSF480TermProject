package ENSF480TermProject.backend.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ENSF480TermProject.backend.models.CreditDiscountCode;

@Repository
public interface CreditDiscountCodeRepository extends JpaRepository<CreditDiscountCode, UUID>{
    
} 
