package ENSF480TermProject.backend.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    @Column(name = "ticket_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    @Column(name = "ticket_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal ticketAmount;

    @Column(name = "movie_id", nullable = false)
    private Long movieId;

    @Column(name = "theatre_id", nullable = false)
    private Long theatreId;

    @Column(name = "seat_number", nullable = false)
    private String seatNumber;

    @Column(name = "buyer_email", nullable = false)
    private String buyerEmail;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;
    
    @Column(name = "showtime_date", nullable = false)
    private LocalDateTime showtimeDate;

    @Column(name = "showtimeId", nullable = false)
    private Long showtimeId;

    public Ticket() {}

    public Ticket(String buyerEmail, BigDecimal ticketAmount, Long movieId, Long theaterId, String seatNumber, LocalDateTime showtimeDate, Long showtimeId) {
        this.buyerEmail = buyerEmail;
        this.ticketAmount = ticketAmount;
        this.movieId = movieId;
        this.seatNumber = seatNumber;
        this.theatreId = theaterId;
        this.creationDate = LocalDateTime.now();
        this.showtimeDate = showtimeDate;
        this.showtimeId = showtimeId;
    }

    //Get
    public Long getTicketId() {
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

    public Long getTheatreId() {
        return theatreId;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getShowtimeDate() {
        return showtimeDate;
    }

    public Long getShowtimeId() {
        return showtimeId;
    }

    //Set
    public void setTicketId(Long ticketId) {
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

    public void setTheatreId(Long theatreId) {
        this.theatreId = theatreId;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setShowtimeDate(LocalDateTime showtimeDate) {
        this.showtimeDate = showtimeDate;
    }

    public void setShowtimeId(Long showtimeId) {
        this.showtimeId = showtimeId;
    }
}
