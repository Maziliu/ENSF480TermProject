package payment.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.Database;
import exceptions.GenericException;
import exceptions.payment.IncorrectTransactionStrategyException;
import payment.Transaction.enums.TransactionType;

public class PurchaseTransactionStrategy implements TransactionStrategy {

    @Override
    public void processTransaction(Transaction transaction) throws GenericException, IncorrectTransactionStrategyException {
        if(!(transaction instanceof Purchase)){
            throw new IncorrectTransactionStrategyException(transaction.transactionType, TransactionType.PURCHASE);
        }

        Database dbInstance = Database.getInstance();

        Purchase purchaseTransaction = (Purchase) transaction;
        
        //Attempt to find user_id
        Integer user_id = null;
        String query = "SELECT * FROM RegisteredUser WHERE email = ?";

        try {
           ResultSet resultSet = dbInstance.executeSelectPreparedStatement(query, purchaseTransaction.getUserEmail());
           if(resultSet.next()){
                user_id = resultSet.getInt("user_id");
           }
        } catch (SQLException e) {
            throw new GenericException("ERROR WHILE ATTEMPTING TO FIND USER_ID WHEN MAKING A PURCHASE TRANSACTION: " + e.getMessage());
        }

        //Create and insert transaction into db
        query = "INSERT INTO transactions (transaction_reference, user_id, user_email, amount, card_number, transaction_type) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            dbInstance.executeDatabaseAlteringPreparedStatement(
                query, 
                purchaseTransaction.getTransactionReference(), 
                String.valueOf(user_id), 
                String.valueOf(purchaseTransaction.getTransactionAmount()),
                purchaseTransaction.getUserPaymentCard().getCardNumber(),
                purchaseTransaction.transactionType.name()
            );
        } catch (SQLException e) {
            throw new GenericException("ERROR WHILE ATTEMPTING TO INSERT INTO TRANSACTIONS WHEN MAKING A PURCHASE TRANSACTION: " + e.getMessage());
        }

        //Generate receipt email and log??
        String receipt = purchaseTransaction.toString();
    }
    
}
