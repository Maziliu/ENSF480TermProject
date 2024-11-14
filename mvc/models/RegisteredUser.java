package mvc.models;
public class RegisteredUser extends User{
    private int userID, theaterCredits;

    public RegisteredUser(int userID, String email, int theaterCredits){
        super(email);
        this.userID = userID;
        this.theaterCredits = theaterCredits;
    }

    public int getUserID() {
        return userID;
    }

    public int getTheaterCredits() {
        return theaterCredits;
    }
}
