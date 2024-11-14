package exceptions.auth;
public class UserNotFoundException extends Exception {
    public UserNotFoundException(){
        super("USER NOT FOUND");
    }
}
