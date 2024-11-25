package ENSF480TermProject.backend.interfaces;

import ENSF480TermProject.backend.dtos.Transaction.TransactionDTO;
import ENSF480TermProject.backend.enums.TransactionType;
import ENSF480TermProject.backend.models.Transaction;
import ENSF480TermProject.backend.repositories.RegisteredUserRepository;
import ENSF480TermProject.backend.repositories.TicketRepository;
import ENSF480TermProject.backend.repositories.TransactionRepository;

public interface TransactionStrategy {
    public TransactionType getType();
    public TransactionDTO processTransaction(Transaction transaction);
}
