package exceptions.payment;

public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(){
        super("Insufficient funds");
    }
}
