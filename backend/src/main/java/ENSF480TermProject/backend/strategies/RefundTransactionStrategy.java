package ENSF480TermProject.backend.strategies;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ENSF480TermProject.backend.dtos.transaction.RefundResponseDTO;
import ENSF480TermProject.backend.enums.RefundStatus;
import ENSF480TermProject.backend.enums.TransactionType;
import ENSF480TermProject.backend.interfaces.TransactionStrategy;
import ENSF480TermProject.backend.models.CreditDiscountCode;
import ENSF480TermProject.backend.models.Purchase;
import ENSF480TermProject.backend.models.Refund;
import ENSF480TermProject.backend.models.Showtime;
import ENSF480TermProject.backend.models.Ticket;
import ENSF480TermProject.backend.models.Transaction;
import ENSF480TermProject.backend.repositories.CreditDiscountCodeRepository;
import ENSF480TermProject.backend.repositories.RegisteredUserRepository;
import ENSF480TermProject.backend.repositories.ShowtimeRepository;
import ENSF480TermProject.backend.repositories.TicketRepository;
import ENSF480TermProject.backend.repositories.TransactionRepository;
import ENSF480TermProject.backend.services.reservation.SeatService;
import ENSF480TermProject.backend.utils.SeatPosition;

@Component
public class RefundTransactionStrategy implements TransactionStrategy{
    
    private final TransactionRepository transactionRepository;
    private final RegisteredUserRepository registeredUserRepository;
    private final TicketRepository ticketRepository;
    private final CreditDiscountCodeRepository creditDiscountCodeRepository;
    private final ShowtimeRepository showtimeRepository;
    private final SeatService seatService;

    @Autowired
    public RefundTransactionStrategy(TransactionRepository transactionRepository, RegisteredUserRepository registeredUserRepository, TicketRepository ticketRepository, CreditDiscountCodeRepository creditDiscountCodeRepository, ShowtimeRepository showtimeRepository, SeatService seatService) {
        this.transactionRepository = transactionRepository;
        this.registeredUserRepository = registeredUserRepository;
        this.ticketRepository = ticketRepository;
        this.creditDiscountCodeRepository = creditDiscountCodeRepository;
        this.showtimeRepository = showtimeRepository;
        this.seatService = seatService;
    }

    @Override
    public TransactionType getType() {
        return TransactionType.REFUND;
    }

    @Override
    public RefundResponseDTO processTransaction(Transaction transaction) {
        RefundResponseDTO refundResponse = new RefundResponseDTO();
        
        Refund refund = (Refund) transaction;
        Long userId = transaction.getUserId();

        //Finds the refund and checks existance
        Optional<Transaction> transactionToRefund = transactionRepository.findById(refund.getRefundedTransactionId());
        if(!transactionToRefund.isPresent()){
            refundResponse.setRefundStatus(RefundStatus.FAILED_INVALID_TRANSACTION_ID);
            return refundResponse;
        }

        Purchase purchaseToRefund = (Purchase) transactionToRefund.get();
        refund.setTransactionAmount(purchaseToRefund.getTransactionAmount());

        //Finds the ticket that associated with purchase and checks existance
        Optional<Ticket> ticket = ticketRepository.findById(purchaseToRefund.getTicketId());
        if (!ticket.isPresent()) {
            refundResponse.setRefundStatus(RefundStatus.INTERNAL_ERROR_TICKET_NOT_FOUND);
        }

        Optional<Showtime> showtime = showtimeRepository.findById(ticket.get().getShowtimeId());

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
  
        //Create the refund transaction in db
        transactionRepository.save(refund);
        
        BigDecimal roundedRefundAmount = refund.getTransactionAmount();
        if(userId == null){ //Guest User
            int refundAmount = roundedRefundAmount.multiply(BigDecimal.valueOf(0.85)).setScale(0, RoundingMode.HALF_UP).intValue();
            CreditDiscountCode creditDiscountCode = creditDiscountCodeRepository.save(new CreditDiscountCode(refundAmount));
            refundResponse.setCreditDiscountCode(creditDiscountCode);
        } else { //RegisteredUser
            registeredUserRepository.addTheatreCredits(userId, roundedRefundAmount.setScale(0, RoundingMode.HALF_UP).intValue());
        }
        
        //Update the existing transaction to REFUDED
        transactionRepository.updateTransactionStatusToRefunded(purchaseToRefund.getTransactionId());

        //Update the showtime to unbook the seat
        seatService.unReserveSeat(ticket.get().getShowtimeId(), new SeatPosition(ticket.get().getSeatNumber()));

        //Complete the response
        refundResponse.setTransaction(refund);
        return refundResponse;
    }   
}
