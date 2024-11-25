package ENSF480TermProject.backend.controllers;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ENSF480TermProject.backend.dtos.Transaction.requests.TicketPurchaseDTO;
import ENSF480TermProject.backend.dtos.Transaction.requests.TicketRefundDTO;
import ENSF480TermProject.backend.dtos.Transaction.responses.PaymentDTO;
import ENSF480TermProject.backend.dtos.Transaction.responses.RefundDTO;
import ENSF480TermProject.backend.models.Purchase;
import ENSF480TermProject.backend.services.payment.TransactionService;

@RestController
@RequestMapping("/transaction")
@CrossOrigin(origins = "http://localhost:3000")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/purchase") 
    public ResponseEntity<PaymentDTO> makePurchase(@RequestBody TicketPurchaseDTO purchaseJSON) {
        return transactionService.makePurchase(purchaseJSON).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build()); 
    }

    @PostMapping("/refund")
    public ResponseEntity<RefundDTO> makeRefund(@RequestBody TicketRefundDTO refundJSON){
        return transactionService.makeRefund(refundJSON).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}

