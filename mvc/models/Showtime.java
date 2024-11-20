package mvc.models;

import java.sql.Timestamp;

public class Showtime {
    private Timestamp time;
    private SeatMap seatmap;

    public Showtime(Timestamp time, int numberOfRows, int seatsPerRow){
        this.time = time;
        this.seatmap = new SeatMap(numberOfRows, seatsPerRow);
    }

    public Timestamp getTime(){
        return time;
    }

    public SeatMap getSeatMap(){
        return seatmap;
    }

    public boolean reserveSeat(int row, char seat){
        return seatmap.reserveSeat(row, seat);
    }

    public boolean cancelReservation(int row, char seat){
        return seatmap.cancelReservation(row, seat);
    }
}
