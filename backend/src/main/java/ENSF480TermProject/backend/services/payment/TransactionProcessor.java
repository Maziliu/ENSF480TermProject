package ENSF480TermProject.backend.services.payment;

import org.springframework.stereotype.Service;

import ENSF480TermProject.backend.dtos.Transaction.PaymentDTO;
import ENSF480TermProject.backend.dtos.Transaction.TransactionDTO;
import ENSF480TermProject.backend.enums.PaymentType;
import ENSF480TermProject.backend.interfaces.TransactionStrategy;
import ENSF480TermProject.backend.models.Transaction;
import ENSF480TermProject.backend.repositories.RegisteredUserRepository;
import ENSF480TermProject.backend.repositories.TicketRepository;
import ENSF480TermProject.backend.repositories.TransactionRepository;

@Service
public class TransactionProcessor {
    private final TransactionRepository transactionRepository;
    private final RegisteredUserRepository registeredUserRepository;
    private final TicketRepository ticketRepository;

    public TransactionProcessor(TransactionRepository transactionRepository, RegisteredUserRepository registeredUserRepository, TicketRepository ticketRepository) {
        this.transactionRepository = transactionRepository;
        this.registeredUserRepository = registeredUserRepository;
        this.ticketRepository = ticketRepository;
    }

    public TransactionDTO processTransaction(Transaction transaction) {
        TransactionStrategy transactionStrategy = transaction.getTransactionStrategy();
        return transactionStrategy.processTransaction(transaction, transactionRepository, registeredUserRepository, ticketRepository);
    }
}

