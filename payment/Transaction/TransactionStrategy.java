package payment.Transaction;

import exceptions.GenericException;
import exceptions.payment.IncorrectTransactionStrategyException;
import exceptions.payment.NullTransactionStrategyException;
import exceptions.payment.TransactionDoesNotExistException;

public interface TransactionStrategy {
    public void processTransaction(Transaction transaction) throws GenericException, TransactionDoesNotExistException, NullTransactionStrategyException, IncorrectTransactionStrategyException;
}
