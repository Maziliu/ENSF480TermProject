package exceptions;
public class UserNotFoundException extends Exception {
    public UserNotFoundException(){
        super("USER NOT FOUND");
    }
}
