package auth;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.Database;
import exceptions.AlreadyLoggedInException;
import exceptions.UserNotFoundException;
import mvc.models.RegisteredUser;

public class Authenticator {
    private RegisteredUser currentUser = null;
    public void createUser(String email, String password){

    }

    public void login(String email, String password) throws UserNotFoundException, AlreadyLoggedInException {
        if(isLoggedIn()){
            throw new AlreadyLoggedInException();
        }

        Database dbInstance = Database.getInstance();

        String query = "SELECT * FROM RegisteredUser WHERE email = ? AND password = ?";
        ResultSet resultSet = dbInstance.executePreparedStatement(query, email, password);

        if(resultSet == null){
            throw new UserNotFoundException();
        }

        try{
            int userId = resultSet.getInt("user_id");
            int theaterCredits = resultSet.getInt("theater_credits");
            currentUser = new RegisteredUser(userId, email, theaterCredits);

        } catch (SQLException e){
            System.err.println("ERROR GETTING USERID OR CREDITS FROM AUTHENTICATOR: " + e.getMessage());
        }
    }

    public void logout(){
        currentUser = null;
    }

    public RegisteredUser getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn(){
        return currentUser == null;
    }

}
