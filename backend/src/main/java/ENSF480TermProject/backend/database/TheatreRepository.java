package ENSF480TermProject.backend.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ENSF480TermProject.backend.models.Movie;
import ENSF480TermProject.backend.models.Theatre;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long> {
    @Query("SELECT t FROM Theatre t LEFT JOIN FETCH t.theatreRooms")
    List<Theatre> findAllWithRooms();

    @Query("SELECT DISTINCT m FROM Movie m " +
        "JOIN m.theatreRooms tr " +
        "JOIN tr.theatre t " +
        "WHERE t.theatreName = :theatreName")
    List<Movie> findMoviesByTheatreName(@Param("theatreName") String theatreName);



}


