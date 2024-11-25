package ENSF480TermProject.backend.strategies;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.swing.text.html.parser.Entity;

import ENSF480TermProject.backend.dtos.Transaction.RefundDTO;
import ENSF480TermProject.backend.dtos.Transaction.TransactionDTO;
import ENSF480TermProject.backend.enums.RefundStatus;
import ENSF480TermProject.backend.interfaces.TransactionStrategy;
import ENSF480TermProject.backend.models.Purchase;
import ENSF480TermProject.backend.models.Refund;
import ENSF480TermProject.backend.models.Showtime;
import ENSF480TermProject.backend.models.Ticket;
import ENSF480TermProject.backend.models.Transaction;
import ENSF480TermProject.backend.repositories.RegisteredUserRepository;
import ENSF480TermProject.backend.repositories.TicketRepository;
import ENSF480TermProject.backend.repositories.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;

public class RefundTransactionStrategy implements TransactionStrategy{
    @Override
    public RefundDTO processTransaction(Transaction transaction, TransactionRepository transactionRepository, RegisteredUserRepository registeredUserRepository, TicketRepository ticketRepository) {
        Refund refund = (Refund) transaction;
        Long userId = transaction.getUserId();
        //Finds the refund and checks existance
        Optional<Transaction> transactionToRefund = transactionRepository.findById(refund.getRefundedTransactionId());
        if(!transactionToRefund.isPresent()){
            return new RefundDTO(RefundStatus.FAILED_INVALID_TRANSACTION_ID, LocalDateTime.now(), null, null);
        }

        Purchase purchaseToRefund = (Purchase) transactionToRefund.get();

        //Finds the ticket that associated with purchase and checks existance
        Optional<Ticket> ticket = ticketRepository.findById(purchaseToRefund.getTicketId());
        if (!ticket.isPresent()) {
            throw new EntityNotFoundException();
        }

        Optional<Showtime> showtime = ticketRepository.findByMovieIdAndTheatreRoomId(ticket.get().getMovieId(), ticket.get().getTheatreId());

        //Finds the showtime associated with ticket and checks existance
        if(!showtime.isPresent()){
            throw new EntityNotFoundException();
        }

        LocalDateTime endOfRefundPeriod = showtime.get().getAirTime().minusHours(72);

        //Check if the showtime is < 72 hours before airtime
        if(LocalDateTime.now().isAfter(endOfRefundPeriod)){
            return new RefundDTO(RefundStatus.FAILED_PAST_REFUND_PERIOD, LocalDateTime.now(), null, null);
        }

        //Update the existing transaction to REFUDED
        transactionRepository.updateTransactionStatusToRefunded(purchaseToRefund.getTransactionId());

        //Create the refund transaction
        transactionRepository.save(refund);

        if(userId == null){ //Guest User
            //Generate the code
            
        }

        return null;
    }   
}
