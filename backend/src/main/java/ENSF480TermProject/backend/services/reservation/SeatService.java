package ENSF480TermProject.backend.services.reservation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ENSF480TermProject.backend.models.Showtime;
import ENSF480TermProject.backend.repositories.ShowtimeRepository;
import ENSF480TermProject.backend.services.payment.TransactionService;
import ENSF480TermProject.backend.utils.SeatPosition;

@Service
public class SeatService {
    private final ShowtimeRepository showtimeRepository;
    private final ObjectMapper objectMapper;

    public SeatService(ShowtimeRepository showtimeRepository, TransactionService transactionService) {
        this.showtimeRepository = showtimeRepository;
        this.objectMapper = new ObjectMapper();
    }

    public List<List<Boolean>> getSeatsByShowtime(Long showtimeId) {
        Showtime showtime = showtimeRepository.findById(showtimeId)
            .orElseThrow(() -> new RuntimeException("Showtime not found"));
        return parseSeatMap(showtime.getSeatMap());
    }

    public SeatPosition reserveSeat(Long showtimeId, SeatPosition position) {
        Showtime showtime = showtimeRepository.findById(showtimeId).orElseThrow(() -> new RuntimeException("Showtime not found"));

        List<List<Boolean>> seatMap = parseSeatMap(showtime.getSeatMap());
        if (seatMap.get(position.getRow()).get(position.getColumn())) {
            throw new RuntimeException("Seat is already reserved");
        }

        seatMap.get(position.getRow()).set(position.getColumn(), true); 
        showtime.setSeatMap(serializeSeatMap(seatMap)); 
        showtimeRepository.save(showtime); 

        return position;
    }

    private List<List<Boolean>> parseSeatMap(String seatMapJson) {
        try {
            return objectMapper.readValue(seatMapJson, new TypeReference<List<List<Boolean>>>() {});
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Invalid JSON format for seat map: " + e.getMessage(), e);
        }
    }

    private String serializeSeatMap(List<List<Boolean>> seatMap) {
        try {
            return objectMapper.writeValueAsString(seatMap);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Failed to serialize seat map to JSON: " + e.getMessage(), e);
        }
    }
}
