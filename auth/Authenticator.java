package auth;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import database.Database;
import exceptions.GenericException;
import exceptions.auth.AlreadyLoggedInException;
import exceptions.auth.UserAlreadyExists;
import exceptions.auth.UserNotFoundException;
import mvc.models.RegisteredUser;

public class Authenticator {
    private RegisteredUser currentUser;

    public Authenticator(){
        currentUser = null;
    }

    public void createUser(String email, String password, String address) throws UserAlreadyExists, GenericException{
        Database dbInstance = Database.getInstance();
        String query = "INSERT INTO RegisteredUser (email, password, address) VALUES (?, ?, ?)";

        try{
            dbInstance.executeDatabaseAlteringPreparedStatement(query, email, password, address);
        } catch (SQLException e){
            if(e instanceof SQLIntegrityConstraintViolationException){
                throw new UserAlreadyExists();
            }
            else {
                throw new GenericException(e.getMessage());
            }
        }
    }

    public void login(String email, String password) throws UserNotFoundException, AlreadyLoggedInException {
        if(isLoggedIn()){
            throw new AlreadyLoggedInException();
        }

        Database dbInstance = Database.getInstance();

        String query = "SELECT * FROM RegisteredUser WHERE email = ? AND password = ?";
        try (ResultSet resultSet = dbInstance.executeSelectPreparedStatement(query, email, password)) {
            if(resultSet == null || !resultSet.next()){
                throw new UserNotFoundException();
            }

            try {
                int userId = resultSet.getInt("user_id");
                int theaterCredits = resultSet.getInt("theater_credits");
                currentUser = new RegisteredUser(userId, email, theaterCredits);

            } catch (SQLException e){
                System.err.println("ERROR GETTING USERID OR CREDITS FROM AUTHENTICATOR: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new UserNotFoundException();
        }
    }

    public void logout(){
        currentUser = null;
    }

    public RegisteredUser getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn(){
        return currentUser != null;
    }

}
