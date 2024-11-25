package ENSF480TermProject.backend.strategies;

import java.math.BigDecimal;
import java.util.Optional;

import ENSF480TermProject.backend.dtos.Transaction.PaymentDTO;
import ENSF480TermProject.backend.enums.TransactionType;
import ENSF480TermProject.backend.interfaces.TransactionStrategy;
import ENSF480TermProject.backend.models.Purchase;
import ENSF480TermProject.backend.models.RegisteredUser;
import ENSF480TermProject.backend.models.Ticket;
import ENSF480TermProject.backend.models.Transaction;
import ENSF480TermProject.backend.repositories.RegisteredUserRepository;
import ENSF480TermProject.backend.repositories.TicketRepository;
import ENSF480TermProject.backend.repositories.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;

public class PurchaseTransactionStrategy implements TransactionStrategy {
    @Override
    public PaymentDTO processTransaction(Transaction transaction, TransactionRepository transactionRepository, RegisteredUserRepository registeredUserRepository, TicketRepository ticketRepository) {
        Purchase purchase = (Purchase) transaction;
        PaymentDTO paymentDTO = null;

        if (purchase.getUserId() == null) {
            paymentDTO = new PaymentDTO(purchase);
        } else {
            Optional<RegisteredUser> userOpt = registeredUserRepository.findById(purchase.getUserId());
            Optional<Integer> theaterCreditsOpt = registeredUserRepository.findTheatreCreditsById(purchase.getUserId());

            if (userOpt.isEmpty() || theaterCreditsOpt.isEmpty()) {
                throw new EntityNotFoundException("User or theater credits not found for userId: " + purchase.getUserId());
            }

            RegisteredUser user = userOpt.get();
            int theaterCredits = theaterCreditsOpt.get();
            BigDecimal creditsAsBigDecimal = BigDecimal.valueOf(theaterCredits);
            BigDecimal transactionAmount = purchase.getTransactionAmount();

            BigDecimal paidByCredits = creditsAsBigDecimal.min(transactionAmount);
            BigDecimal paidByUser = transactionAmount.subtract(paidByCredits);
            registeredUserRepository.subtractTheatreCredits(user.getUserId(), paidByCredits.intValue());

            paymentDTO = new PaymentDTO(purchase, user, paidByCredits, paidByUser);
        }

        Ticket savedTicket = ticketRepository.save(purchase.getTicket());
        purchase.setTicket(savedTicket);
        transactionRepository.save(purchase);
        return paymentDTO;
    }
}

