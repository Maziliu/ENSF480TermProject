package ENSF480TermProject.backend.services.payment;

import org.springframework.stereotype.Service;

import ENSF480TermProject.backend.dtos.Transaction.PaymentDTO;
import ENSF480TermProject.backend.enums.PaymentType;
import ENSF480TermProject.backend.interfaces.TransactionStrategy;
import ENSF480TermProject.backend.models.Transaction;
import ENSF480TermProject.backend.repositories.RegisteredUserRepository;
import ENSF480TermProject.backend.repositories.TransactionRepository;

@Service
public class TransactionProcessor {
    private final TransactionRepository transactionRepository;
    private final RegisteredUserRepository registeredUserRepository;

    public TransactionProcessor(TransactionRepository transactionRepository, RegisteredUserRepository registeredUserRepository) {
        this.transactionRepository = transactionRepository;
        this.registeredUserRepository = registeredUserRepository;
    }

    public PaymentDTO processTransaction(Transaction transaction) {
        TransactionStrategy transactionStrategy = transaction.getTransactionStrategy();
        return transactionStrategy.proccessTransaction(transaction, transactionRepository, registeredUserRepository);
    }
}

