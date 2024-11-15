package exceptions.payment;

import payment.Transaction.enums.TransactionType;

public class IncorrectTransactionStrategyException extends Exception {
    public IncorrectTransactionStrategyException(TransactionType transactionTypeX, TransactionType transactionTypeY) {
        super("INCORRECT TRANSACTION STRATEGY WAS APPLIED. TRIED TO PROCESS " + transactionTypeX.name() + " AS " + transactionTypeY.name());
    }
}
