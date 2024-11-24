package ENSF480TermProject.backend.services.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ENSF480TermProject.backend.database.TheatreRepository;
import ENSF480TermProject.backend.models.Movie;
import ENSF480TermProject.backend.models.Theatre;
import jakarta.transaction.Transactional;

@Service
public class TheatreSearchService {

    private TheatreRepository theatreRepository;

    @Autowired
    public TheatreSearchService(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAllWithRoomsAndShowtimes();
    }

    public List<Movie> getAllMoviesAtSpecificTheatre(String theatreName) {
        return theatreRepository.findMoviesByTheatreName(theatreName);
    }
}
