package ENSF480TermProject.backend.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ENSF480TermProject.backend.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, UUID>{
    
}
