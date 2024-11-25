package ENSF480TermProject.backend.interfaces;

import ENSF480TermProject.backend.dtos.Transaction.PaymentDTO;
import ENSF480TermProject.backend.models.Transaction;
import ENSF480TermProject.backend.repositories.RegisteredUserRepository;
import ENSF480TermProject.backend.repositories.TransactionRepository;

public interface TransactionStrategy {
    public PaymentDTO proccessTransaction(Transaction transaction, TransactionRepository transactionRepository, RegisteredUserRepository registeredUserRepository);
}
