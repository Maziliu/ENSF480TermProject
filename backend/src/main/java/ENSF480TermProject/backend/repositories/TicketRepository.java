package ENSF480TermProject.backend.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ENSF480TermProject.backend.models.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{  
    Optional<Ticket> findByCreationDate(LocalDateTime creationDate);
} 