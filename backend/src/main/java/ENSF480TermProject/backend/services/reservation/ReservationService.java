package ENSF480TermProject.backend.services.reservation;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ENSF480TermProject.backend.dtos.reservation.ReservationResponseDTO;
import ENSF480TermProject.backend.dtos.transaction.PaymentResponseDTO;
import ENSF480TermProject.backend.dtos.transaction.TicketPurchaseRequestDTO;
import ENSF480TermProject.backend.models.Purchase;
import ENSF480TermProject.backend.models.Showtime;
import ENSF480TermProject.backend.services.payment.TransactionService;
import ENSF480TermProject.backend.utils.SeatPosition;

@Service
public class ReservationService {
    private final TransactionService transactionService;
    private final SeatService seatService;

    public ReservationService(TransactionService transactionService, SeatService seatService) {
        this.transactionService = transactionService;
        this.seatService = seatService;
    }

    public Optional<PaymentResponseDTO> processReservation(TicketPurchaseRequestDTO ticketPurchaseRequestDTO){
        SeatPosition seatPosition = seatService.reserveSeat(ticketPurchaseRequestDTO.getShowtime().getId(), ticketPurchaseRequestDTO.getSeatPosition());
        PaymentResponseDTO paymentResponseDTO = transactionService.makePurchase(ticketPurchaseRequestDTO).get();

        return Optional.of(paymentResponseDTO);
    }
}
