package mvc.models;
public class RegisteredUser {
    private int userID, theaterCredits;
    private String email;

    public RegisteredUser(int userID, String email, int theaterCredits){
        this.userID = userID;
        this.email = email;
        this.theaterCredits = theaterCredits;
    }

    public int getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public int getTheaterCredits() {
        return theaterCredits;
    }
}
