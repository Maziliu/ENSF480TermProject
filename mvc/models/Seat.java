package mvc.models;

public class Seat {
    private int row;
    private char seat;
    private boolean isOccupied;

    public Seat(int row, char seat) {
        this.row = row;
        this.seat = seat;
        this.isOccupied = false;
    }

    public int getRow() {
        return row;
    }

    public char getSeat() {
        return seat;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void occupySeat() {
        this.isOccupied = true;
    }

    public void vacateSeat() {
        this.isOccupied = false;
    }

    @Override
    public String toString() {
        return isOccupied ? "X" : " ";
    }
}
