package ENSF480TermProject.backend.models;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Tickets")
public class Ticket {
    @Id
    @Column(name = "ticket_id", nullable = false, updatable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ticketId;

    @Column(name = "ticket_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal ticketAmount;

    @Column(name = "movie_id", nullable = false)
    private Long movieId;

    @Column(name = "theatre_id", nullable = false)
    private Long theatreId;

    @Column(name = "seat_number", nullable = false)
    private String seatNumber;

    public Ticket() {}

    public Ticket(BigDecimal ticketAmount, Long movieId, Long theaterId, String seatNumber) {
        this.ticketAmount = ticketAmount;
        this.movieId = movieId;
        this.seatNumber = seatNumber;
    }

    //Get
    public UUID getTicketId() {
        return ticketId;
    }

    public BigDecimal getTicketAmount() {
        return ticketAmount;
    }

    public Long getMovieId() {
        return movieId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    //Set
    public void setTicketId(UUID ticketId) {
        this.ticketId = ticketId;
    }

    public void setTicketAmount(BigDecimal ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    
}
