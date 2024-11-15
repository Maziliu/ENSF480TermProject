package payment.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

import database.Database;
import exceptions.GenericException;
import exceptions.payment.IncorrectTransactionStrategyException;
import exceptions.payment.TransactionDoesNotExistException;
import payment.Transaction.enums.TransactionType;

public class RefundTransactionStrategy implements TransactionStrategy {

    @Override
    public void processTransaction(Transaction transaction) throws TransactionDoesNotExistException, GenericException, IncorrectTransactionStrategyException {
        if(!(transaction instanceof Purchase)){
            throw new IncorrectTransactionStrategyException(transaction.transactionType, TransactionType.REFUND);
        }

        Database dbInstance = Database.getInstance();
        Refund refundTransaction = (Refund) transaction;
        String cardNumber = null;
        Integer user_id = null;

        //Find transaction in db
        String query = "SELECT * FROM transactions WHERE transaction_reference = ?";
        try {
            ResultSet resultSet = dbInstance.executeSelectPreparedStatement(query, refundTransaction.getTransactionReferenceOfPurchase());
            if(!resultSet.next()){
                throw new TransactionDoesNotExistException();
            }
            refundTransaction.setUserEmail(resultSet.getString("user_email"));
            refundTransaction.setTransactionAmount(resultSet.getDouble("amount"));
            cardNumber = resultSet.getString("card_number");
            user_id = resultSet.getInt("user_id");
        } catch (SQLException e) {
            throw new GenericException("ERROR WHILE ATTEMPTING FIND TRANSACTION WHEN MAKING A REFUND TRANSACTION: " + e.getMessage());
        }

        //Create refund transaction and insert
        query = "INSERT INTO transactions (transaction_reference, user_id, user_email, amount, card_number, transaction_type) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            dbInstance.executeDatabaseAlteringPreparedStatement(
                query, 
                refundTransaction.getTransactionReference(), 
                String.valueOf(user_id), 
                String.valueOf(refundTransaction.getTransactionAmount()),
                cardNumber,
                refundTransaction.transactionType.name()
            );
        } catch (SQLException e) {
            throw new GenericException("ERROR WHILE ATTEMPTING TO INSERT INTO TRANSACTIONS WHEN MAKING A REFUND TRANSACTION: " + e.getMessage());
        }

        //Update registered user theater creds or send user email with creds code
    }

    public String generateCreditCode(double refundAmount) throws GenericException {
        String creditsCode;
        Database dbInstance = Database.getInstance();

        do{
            creditsCode = "CRD-" + UUID.randomUUID().toString();
            String query = "SELECT COUNT(*) FROM CreditCodes WHERE code = ?";
            try {
                ResultSet resultSet = dbInstance.executeSelectPreparedStatement(query, creditsCode);
                resultSet.next();
                int count = resultSet.getInt(1);
                if(count == 0){
                    break;
                }

            } catch (SQLException e){
                System.err.println("ERROR GENERATING CRD: " + e.getMessage());
            } 
        } while(true);


        String query = "INSERT INTO creditcodes(code, credit_amount, expiration_date) VALUES (?, ?, ?)";
        try {
            dbInstance.executeDatabaseAlteringPreparedStatement(query, creditsCode, String.valueOf(refundAmount), String.valueOf(LocalDate.now().plusYears(1)));
        } catch (SQLException e) {
            throw new GenericException("ERROR OCCURED AT REFUNDTRANSACTIONSTRATEGY WHEN ATTMPTING TO INSERT NEWLY GENERATED CREDITSCODE: " + e.getMessage());
        }

        return creditsCode;
    }
    
}
