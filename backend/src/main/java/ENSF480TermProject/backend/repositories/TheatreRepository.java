package ENSF480TermProject.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ENSF480TermProject.backend.models.Movie;
import ENSF480TermProject.backend.models.Theatre;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long> {

    @Query("SELECT DISTINCT m FROM Movie m " +
        "JOIN m.showtimes s " +
        "JOIN s.theatreRoom r " +
        "JOIN r.theatre t " +
        "WHERE t.theatreName = :theatreName")
    List<Movie> findMoviesByTheatreName(@Param("theatreName") String theatreName);

    @Query("SELECT DISTINCT t FROM Theatre t " +
        "LEFT JOIN FETCH t.theatreRooms r " +
        "LEFT JOIN FETCH r.showtimes")
    List<Theatre> findAllWithRoomsAndShowtimes();
}




