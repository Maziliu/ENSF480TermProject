package ENSF480TermProject.backend.utils;

public class SeatPosition {
    private int row;
    private int column;

    public SeatPosition() {}

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

