package ENSF480TermProject.backend.utils;

import java.util.List;

public class SeatPosition {
    private int row;
    private int column;

    public SeatPosition() {}

    //for parsing the string version kept in ticket
    public SeatPosition(String seatPosition){
        String[] tokens = seatPosition.split(", ");
        this.row = Integer.parseInt(tokens[0].replaceAll("\\D", ""));
        this.column = Integer.parseInt(tokens[1].replaceAll("\\D", ""));
    }

    public SeatPosition(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "Row " + String.valueOf(row) + ", Column " + String.valueOf(column);
    }
}

