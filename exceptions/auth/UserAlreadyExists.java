package exceptions.auth;

public class UserAlreadyExists extends Exception{
    public UserAlreadyExists(){
        super("Email is already associated with another account");
    }
}
