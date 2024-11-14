package exceptions;

public class AlreadyLoggedInException extends Exception{
    public AlreadyLoggedInException(){
        super("Already logged in, log user out first");
    }
}
