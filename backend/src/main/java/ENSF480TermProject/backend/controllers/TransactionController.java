package ENSF480TermProject.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ENSF480TermProject.backend.dtos.Transaction.PaymentDTO;
import ENSF480TermProject.backend.dtos.Transaction.TicketPurchaseDTO;
import ENSF480TermProject.backend.services.payment.PaymentService;

@RestController
@RequestMapping("/transaction")
@CrossOrigin(origins = "http://localhost:3000")
public class TransactionController {

    private final PaymentService paymentService;

    public TransactionController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/purchase") 
    public ResponseEntity<PaymentDTO> makePurchase(@RequestBody TicketPurchaseDTO purchaseJSON) {
        return paymentService.makePurchase(purchaseJSON).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build()); 
    }
}

