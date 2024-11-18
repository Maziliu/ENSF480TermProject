package mvc.models;

import java.sql.Date;

public class Showtime {
    private Movie movie;
    private Date time;
    private SeatMap seatmap;

    public Showtime(Movie movie, Date time, int numberOfRows, int seatsPerRow){
        this.movie = movie;
        this.time = time;
        this.seatmap = new SeatMap(numberOfRows, seatsPerRow);
    }

    public Movie getMovie(){
        return movie;
    }

    public Date getTime(){
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
