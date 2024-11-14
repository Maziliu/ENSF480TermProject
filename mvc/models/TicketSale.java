package mvc.models;

public class TicketSale {
    private double amount;
    private Ticket ticket;

    public TicketSale(double amount, Ticket ticket) {
        this.amount = amount;
        this.ticket = ticket;
    }

    public double getAmount() {
        return amount;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
