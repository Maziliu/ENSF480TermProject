package payment;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

import database.Database;
import exceptions.GenericException;
import exceptions.payment.IncorrectTransactionStrategyException;
import exceptions.payment.NullTransactionStrategyException;
import exceptions.payment.TransactionDoesNotExistException;
import payment.Transaction.Purchase;
import payment.Transaction.PurchaseTransactionStrategy;
import payment.Transaction.Refund;
import payment.Transaction.RefundTransactionStrategy;
import payment.Transaction.Transaction;
import payment.Transaction.TransactionStrategy;

public class TransactionProcessor implements TransactionStrategy {
    PaymentMethod paymentMethod;
    TransactionStrategy transactionStrategy;

    public TransactionProcessor(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    
    private String generateTransactionReference() {
        String transactionReference;
        Database dbInstance = Database.getInstance();

        do{
            transactionReference = "TXN-" + UUID.randomUUID().toString();
            String query = "SELECT COUNT(*) FROM transaction WHERE transaction_reference = ?";
            try {
                ResultSet resultSet = dbInstance.executeSelectPreparedStatement(query, transactionReference);
                resultSet.next();
                int count = resultSet.getInt(1);
                if(count == 0){
                    break;
                }

            } catch (SQLException e){
                System.err.println("ERROR GENERATING TXN: " + e.getMessage());
            } 
        } while(true);


        return transactionReference;
    }



    @Override
    public void processTransaction(Transaction transaction) throws GenericException, TransactionDoesNotExistException, NullTransactionStrategyException, IncorrectTransactionStrategyException {
        //Check for valid transactionStrategy
        if(transactionStrategy == null){
            throw new NullTransactionStrategyException();
        }

        //Setup the transaction
        transaction.setTransactionReference(generateTransactionReference());
        transaction.setTransactionDate(Date.valueOf(LocalDate.now()));

        //Do the processing
        transactionStrategy.processTransaction(transaction);
    }

    public void setTransactionStrategy(TransactionStrategy transactionStrategy) {
        this.transactionStrategy = transactionStrategy;
    }

    
}
