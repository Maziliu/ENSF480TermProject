package ENSF480TermProject.backend.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ENSF480TermProject.backend.models.Transaction;
import jakarta.transaction.Transactional;

public interface TransactionRepository extends JpaRepository<Transaction, UUID>{
    @Transactional
    @Modifying
    @Query("UPDATE Transaction t SET t.paymentStatus = 'REFUNDED' WHERE t.transactionId = :transactionId")
    int updateTransactionStatusToRefunded(@Param("transactionId") UUID transactionId);
}
