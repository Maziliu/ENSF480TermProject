package exceptions.payment;

public class TransactionDoesNotExistException extends PaymentException {
    public TransactionDoesNotExistException() {
        super("Transaction does not exist in the database");
    }

    public TransactionDoesNotExistException(String message) {
        super(message);
    }
}
