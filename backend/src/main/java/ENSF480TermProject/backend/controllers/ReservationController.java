package ENSF480TermProject.backend.controllers;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ENSF480TermProject.backend.dtos.transaction.PaymentResponseDTO;
import ENSF480TermProject.backend.dtos.transaction.RefundResponseDTO;
import ENSF480TermProject.backend.dtos.transaction.TicketPurchaseRequestDTO;
import ENSF480TermProject.backend.dtos.transaction.TicketRefundRequestDTO;
import ENSF480TermProject.backend.models.Purchase;
import ENSF480TermProject.backend.services.payment.TransactionService;
import ENSF480TermProject.backend.services.reservation.ReservationService;

@RestController
@RequestMapping("/transaction")
@CrossOrigin(origins = "http://localhost:3000")
public class ReservationController {

    private final TransactionService transactionService;
    private final ReservationService reservationService;

    public ReservationController(TransactionService transactionService, ReservationService reservationService) {
        this.transactionService = transactionService;
        this.reservationService = reservationService;
    }

    @PostMapping("/purchase") 
    public ResponseEntity<PaymentResponseDTO> makePurchase(@RequestBody TicketPurchaseRequestDTO purchaseJSON) {
        return reservationService.processReservation(purchaseJSON).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build()); 
    }

    @PostMapping("/refund")
    public ResponseEntity<RefundResponseDTO> makeRefund(@RequestBody TicketRefundRequestDTO refundJSON){
        return transactionService.makeRefund(refundJSON).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}

