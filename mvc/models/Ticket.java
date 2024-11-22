package mvc.models;

import java.sql.Date;

public class Ticket {
    private int seatNumber;
    private Movie movie;
    // private Theater theater;
    private Date showTime;

    public Ticket(Movie movie, int seatNumber) {
        this.movie = movie;
        this.seatNumber = seatNumber;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public Date getShowTime() {
        return showTime;
    }

    // public Theater getTheater() {
    //     return theater;
    // }
}
