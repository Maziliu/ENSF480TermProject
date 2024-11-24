package ENSF480TermProject.backend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ENSF480TermProject.backend.services.reservation.SeatService;

@RestController
@RequestMapping("/seats")
@CrossOrigin(origins = "http://localhost:3000")
public class SeatController {
    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/{showtime_id:[0-9]+}")
    public ResponseEntity<List<List<Boolean>>> getSeatMapByShowtimeId(@PathVariable Long showtime_id){
        return ResponseEntity.ok(seatService.getSeatsByShowtime(showtime_id));
    }
}
