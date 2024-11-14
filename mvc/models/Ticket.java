package mvc.models;

public class Ticket {
    private int seatNumber;
    private Movie movie;

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
}
