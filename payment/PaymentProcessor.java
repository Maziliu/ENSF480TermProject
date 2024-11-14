package payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import database.Database;
import exceptions.GenericException;
import mvc.models.TicketSale;
import mvc.models.User;

public class PaymentProcessor {
    PaymentMethod paymentMethod;

    public PaymentProcessor(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    void processPayment(User user, String cardNumber, TicketSale ticketSale) throws GenericException, SQLException {
        String transactionReference = generateTransactionReference();
        Database dbInstance = Database.getInstance();
        String userEmail = user.getEmail();
        String amount = String.valueOf(ticketSale.getAmount());

        String query = "INSERT INTO transactions (user_email, tansaction_reference, amount, payment_method, card_number) VALUES(?, ?, ?, ?, ?)";
        dbInstance.executeDatabaseAlteringPreparedStatement(query, userEmail, transactionReference, amount, paymentMethod.toString(), cardNumber);

        generateRecipt(transactionReference, amount, cardNumber, ticketSale);
    }

    void processRefund(String transactionReference){
        
    }

    private String generateTransactionReference() throws GenericException {
        String transactionReference;
        Database dbInstance = Database.getInstance();

        do{
            transactionReference = "TXN-" + UUID.randomUUID().toString();
            String query = "SELECT COUNT(*) FROM transactions WHERE transaction_reference = ?";
            try {
                ResultSet resultSet = dbInstance.executeSelectPreparedStatement(query, transactionReference);
                resultSet.next();
                int count = resultSet.getInt(1);
                if(count == 0){
                    break;
                }

            } catch (SQLException e){
                throw new GenericException("ERROR GENERATING TXN: " + e.getMessage());
            } 
        } while(true);


        return transactionReference;
    }

    private void generateRecipt(String transactionReference, String amount, String cardNumber, TicketSale ticketSale){
        String receipt = String.format(
                "-------- Receipt --------%n" +
                "Transaction Ref: %s%n" +
                "Amount: $%.2f%n" +
                "Card Number: %s%n" +
                "Item: %s%n" +
                "-------------------------",
                transactionReference, amount, cardNumber, ticketSale.getTicket().getMovie().getName()
        );

        System.out.println(receipt);
    }
}
