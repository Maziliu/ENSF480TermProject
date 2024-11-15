package exceptions.payment;

public class IncorrectPaymentInformationException extends PaymentException {
    public IncorrectPaymentInformationException() {
        super("Incorrect payment information");
    }
}
