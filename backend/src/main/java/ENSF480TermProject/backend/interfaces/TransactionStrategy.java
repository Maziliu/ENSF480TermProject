package ENSF480TermProject.backend.interfaces;

import ENSF480TermProject.backend.dtos.Transaction.TransactionDTO;
import ENSF480TermProject.backend.models.Transaction;
import ENSF480TermProject.backend.repositories.RegisteredUserRepository;
import ENSF480TermProject.backend.repositories.TicketRepository;
import ENSF480TermProject.backend.repositories.TransactionRepository;

public interface TransactionStrategy {
    TransactionDTO processTransaction(Transaction transaction, TransactionRepository transactionRepository, RegisteredUserRepository registeredUserRepository, TicketRepository ticketRepository);
}
