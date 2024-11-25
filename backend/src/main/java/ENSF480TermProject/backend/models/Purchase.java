package ENSF480TermProject.backend.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import ENSF480TermProject.backend.strategies.PurchaseTransactionStrategy;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
public class Purchase extends Transaction {
    @Column(name = "item_description")
    private String itemDescription;

    @Column(name = "ticket_id")
    private Long ticketId;

    @Transient
    Ticket ticket;

    
    public Purchase(Ticket ticket, RegisteredUser registeredUser, BigDecimal transactionAmount) {
        super(transactionAmount, LocalDateTime.now(), registeredUser.getUserId(), registeredUser.getEmail());
        this.ticketId = ticket.getTicketId();
        this.ticket = ticket;
    }

    public Purchase(Ticket ticket, String userEmail, BigDecimal transactionAmount) {
        super(transactionAmount, LocalDateTime.now(), null, userEmail);
        this.ticketId = ticket.getTicketId();
        this.ticket = ticket;
    }

    public Purchase() { }

    //Get
    public String getItemDescription() {
        return itemDescription;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public Long getTicketId() {
        return ticketId;
    }

    //Set
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public void setTicket(Ticket ticket) {
        this.ticketId = ticket.getTicketId();
        this.ticket = ticket;
    }


}