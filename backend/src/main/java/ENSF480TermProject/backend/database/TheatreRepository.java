package ENSF480TermProject.backend.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ENSF480TermProject.backend.models.Theatre;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long> {
    @Query("SELECT t FROM Theatre t LEFT JOIN FETCH t.rooms")
    List<Theatre> findAllWithRooms();
} 

