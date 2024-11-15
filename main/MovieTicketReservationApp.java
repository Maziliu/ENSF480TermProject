package main;

import java.util.UUID;

import auth.Authenticator;
import exceptions.GenericException;
import exceptions.auth.AlreadyLoggedInException;
import exceptions.auth.UserAlreadyExists;
import exceptions.auth.UserNotFoundException;
import payment.Transaction.RefundTransactionStrategy;
import payment.Transaction.TransactionStrategy;

public class MovieTicketReservationApp {
    Authenticator auth;
    public MovieTicketReservationApp(){
        auth = new Authenticator();
    }

    public void runApp(){
        // try {
        //     auth.createUser("test@gmail.com", "1234", "123 test st ne");
        // } catch (UserAlreadyExists e) {
        //     e.printStackTrace();
        // } catch (GenericException e) {
        //     e.printStackTrace();
        // }

        // try {
        //     auth.login("test@gmail.com", "1234");
        // } catch (UserNotFoundException e) {
        //     e.printStackTrace();
        // } catch (AlreadyLoggedInException e) {
        //     e.printStackTrace();
        // }

        // System.out.println(auth.getCurrentUser().getEmail());

        // System.out.println(UUID.randomUUID().toString());

        RefundTransactionStrategy t = new RefundTransactionStrategy();
        System.out.println(t.generateCreditCode(69.99));
    }

    public static void main(String[] args) {
        MovieTicketReservationApp app = new MovieTicketReservationApp();
        app.runApp();
    }
}
