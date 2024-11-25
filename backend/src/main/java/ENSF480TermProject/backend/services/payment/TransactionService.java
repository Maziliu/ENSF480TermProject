package ENSF480TermProject.backend.services.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import ENSF480TermProject.backend.dtos.Transaction.PaymentDTO;
import ENSF480TermProject.backend.dtos.Transaction.RefundDTO;
import ENSF480TermProject.backend.dtos.Transaction.TicketPurchaseDTO;
import ENSF480TermProject.backend.dtos.Transaction.TicketRefundDTO;
import ENSF480TermProject.backend.enums.TransactionType;
import ENSF480TermProject.backend.interfaces.TransactionStrategy;
import ENSF480TermProject.backend.models.Purchase;
import ENSF480TermProject.backend.models.Refund;
import ENSF480TermProject.backend.models.RegisteredUser;
import ENSF480TermProject.backend.models.Ticket;
import ENSF480TermProject.backend.models.Transaction;
import ENSF480TermProject.backend.repositories.RegisteredUserRepository;
import ENSF480TermProject.backend.repositories.TransactionRepository;

@Service
public class TransactionService {

    private final RegisteredUserRepository registeredUserRepository;
    private final TransactionProcessor transactionProcessor;

    @Autowired
    public TransactionService(RegisteredUserRepository registeredUserRepository, TransactionProcessor transactionProcessor) {
        this.registeredUserRepository = registeredUserRepository;
        this.transactionProcessor = transactionProcessor;
    }

    private Long findUserIdFromDatabaseByEmail(String userEmail){
        Optional<RegisteredUser> user = registeredUserRepository.findByEmail(userEmail);
        return user.isPresent() ? user.get().getUserId() : null;
    }

    public Optional<PaymentDTO> makePurchase(TicketPurchaseDTO ticketPurchaseDTO) {
        Optional<RegisteredUser> user = registeredUserRepository.findByEmail(ticketPurchaseDTO.getEmail());
        Long userId = user.isPresent() ? user.get().getUserId() : null;

        Ticket ticket = new Ticket(ticketPurchaseDTO.getEmail(), ticketPurchaseDTO.getTicketPrice(),ticketPurchaseDTO.getShowtime().getMovie_id(), ticketPurchaseDTO.getShowtime().getTheatre_id(), ticketPurchaseDTO.getSeatName());
        Purchase purchase = (userId == null) ? new Purchase(ticket, ticketPurchaseDTO.getEmail(), ticketPurchaseDTO.getTotalPrice()) : new Purchase(ticket, user.get(), ticketPurchaseDTO.getTotalPrice());

        PaymentDTO paymentDTO = (PaymentDTO) transactionProcessor.processTransaction(purchase, TransactionType.PURCHASE);
        return Optional.of(paymentDTO);
    }

    public Optional<RefundDTO> makeRefund(TicketRefundDTO ticketRefundDTO){
        Long userId = findUserIdFromDatabaseByEmail(ticketRefundDTO.getUserEmail());

        Refund transaction = new Refund(LocalDateTime.now(), userId, ticketRefundDTO.getUserEmail(), ticketRefundDTO.getTransactionId());

        RefundDTO refundDTO = (RefundDTO) transactionProcessor.processTransaction(transaction, TransactionType.REFUND);
        return Optional.of(refundDTO);
    }
}



