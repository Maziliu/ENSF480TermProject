package mvc.models;

import java.util.ArrayList;
import java.util.List;

public class SeatMap {
    private int numRows;
    private int seatsPerRow;
    private List<Seat> seatList;

    public SeatMap(int numRows, int seatsPerRow) {
        this.numRows = numRows;
        this.seatsPerRow = seatsPerRow;
        this.seatList = new ArrayList<>();

        // Initialize the seat list
        for (int i = 1; i <= numRows; i++) {
            for (char j = 'A'; j < 'A' + seatsPerRow; j++) {
                seatList.add(new Seat(i, j));
            }
        }
    }

    public void showSeatMap() {
        System.out.println("Theater Seat Map:");
        System.out.print("     ");
        for (int i = 0; i < seatsPerRow; i++) {
            System.out.print((char) ('A' + i) + "   ");
        }
        System.out.println();

        for (int i = 1; i <= numRows; i++) {
            System.out.print(String.format("%2d ", i));
            for (int j = 0; j < seatsPerRow; j++) {
                Seat seat = getSeat(i, (char) ('A' + j));
                System.out.print("| " + seat + " ");
            }
            System.out.println("|");
            System.out.print("   ");
            for (int j = 0; j < seatsPerRow; j++) {
                System.out.print("+---");
            }
            System.out.println("+");
        }
    }

    public Seat getSeat(int row, char seat) {
        return seatList.stream()
            .filter(s -> s.getRow() == row && s.getSeat() == seat)
            .findFirst()
            .orElse(null);
    }

    public boolean reserveSeat(int row, char seat) {
        Seat targetSeat = getSeat(row, seat);
        if (targetSeat != null && !targetSeat.isOccupied()) {
            targetSeat.occupySeat();
            System.out.println("Seat " + row + seat + " has been reserved.");
            return true;
        } else {
            System.out.println("Seat " + row + seat + " is unavailable or already reserved.");
            return false;
        }
    }

    public boolean cancelReservation(int row, char seat) {
        Seat targetSeat = getSeat(row, seat);
        if (targetSeat != null && targetSeat.isOccupied()) {
            targetSeat.vacateSeat();
            System.out.println("Reservation for seat " + row + seat + " has been canceled.");
            return true;
        } else {
            System.out.println("Seat " + row + seat + " is not reserved.");
            return false;
        }
    }
}
