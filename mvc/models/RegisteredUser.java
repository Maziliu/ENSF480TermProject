package mvc.models;

import java.util.ArrayList;

public class RegisteredUser extends User{
    private int userID, theaterCredits;
    private ArrayList<PaymentCard> paymentCards;

    public RegisteredUser(int userID, String email, int theaterCredits, PaymentCard... cards){
        super(email);
        this.userID = userID;
        this.theaterCredits = theaterCredits;
        
        paymentCards = new ArrayList<>();
        for(PaymentCard card : cards){
            paymentCards.add(card);
        }
    }

    public int getUserID() {
        return userID;
    }

    public int getTheaterCredits() {
        return theaterCredits;
    }
}
