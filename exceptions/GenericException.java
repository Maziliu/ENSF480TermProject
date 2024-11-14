package exceptions;

public class GenericException extends Exception{
    public GenericException(){
        super("An Error has occured");
    }

    public GenericException(String message){
        super(message);
    }
}
