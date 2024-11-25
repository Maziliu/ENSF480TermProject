package ENSF480TermProject.backend.strategies;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.swing.text.html.parser.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ENSF480TermProject.backend.dtos.Transaction.RefundDTO;
import ENSF480TermProject.backend.dtos.Transaction.TransactionDTO;
import ENSF480TermProject.backend.enums.RefundStatus;
import ENSF480TermProject.backend.enums.TransactionStatus;
import ENSF480TermProject.backend.enums.TransactionType;
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

@Component
public class RefundTransactionStrategy implements TransactionStrategy{
    
    private final TransactionRepository transactionRepository;
    private final RegisteredUserRepository registeredUserRepository;
    private final TicketRepository ticketRepository;

    @Autowired
    public RefundTransactionStrategy(TransactionRepository transactionRepository, RegisteredUserRepository registeredUserRepository, TicketRepository ticketRepository) {
        this.transactionRepository = transactionRepository;
        this.registeredUserRepository = registeredUserRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public TransactionType getType() {
        return TransactionType.REFUND;
    }

    @Override
    public RefundDTO processTransaction(Transaction transaction) {
        RefundDTO refundResponse = new RefundDTO();
        
        Refund refund = (Refund) transaction;
        Long userId = transaction.getUserId();

        //Finds the refund and checks existance
        Optional<Transaction> transactionToRefund = transactionRepository.findById(refund.getRefundedTransactionId());
        if(!transactionToRefund.isPresent()){
            refundResponse.setRefundStatus(RefundStatus.FAILED_INVALID_TRANSACTION_ID);
            return refundResponse;
        }

        Purchase purchaseToRefund = (Purchase) transactionToRefund.get();

        //Finds the ticket that associated with purchase and checks existance
        Optional<Ticket> ticket = ticketRepository.findById(purchaseToRefund.getTicketId());
        if (!ticket.isPresent()) {
            refundResponse.setRefundStatus(RefundStatus.INTERNAL_ERROR_TICKET_NOT_FOUND);
        }

        Optional<Showtime> showtime = ticketRepository.findByMovieIdAndTheatreRoomId(ticket.get().getMovieId(), ticket.get().getTheatreId());

        //Finds the showtime associated with ticket and checks existance
        if(!showtime.isPresent()){
            refundResponse.setRefundStatus(RefundStatus.INTERNAL_ERROR_SHOWTIME_NOT_FOUND);
            return refundResponse;
        }

        LocalDateTime endOfRefundPeriod = showtime.get().getAirTime().minusHours(72);

        //Check if the showtime is < 72 hours before airtime
        if(LocalDateTime.now().isAfter(endOfRefundPeriod)){
            refundResponse.setRefundStatus(RefundStatus.FAILED_PAST_REFUND_PERIOD);
            return refundResponse;
        }

        //Update the existing transaction to REFUDED
        transactionRepository.updateTransactionStatusToRefunded(purchaseToRefund.getTransactionId());

        //Create the refund transaction
        transactionRepository.save(refund);

        
        if(userId == null){ //Guest User
            //Generate the code
            
        } else { //RegisteredUser

        }

        //Complete the response
        refundResponse.setTransaction(refund);
        return refundResponse;
    }   
}
