package exceptions.payment;

public class NullTransactionStrategyException extends Exception {
    public NullTransactionStrategyException() {
        super("TRANSACTION STRATEGY WAS NOT SET BEFORE ATTEMPTING TO PROCESS A TRANSACTION");
    }
}
