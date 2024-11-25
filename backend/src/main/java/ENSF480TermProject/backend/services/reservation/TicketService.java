package ENSF480TermProject.backend.services.reservation;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ENSF480TermProject.backend.models.Ticket;
import ENSF480TermProject.backend.repositories.TicketRepository;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public void saveTicket(Ticket ticket){
        ticketRepository.save(ticket);
    }

    public Optional<Ticket> findTicketByCreationDate(LocalDateTime creationDate){
        return ticketRepository.findByCreationDate(creationDate);
    }
}
