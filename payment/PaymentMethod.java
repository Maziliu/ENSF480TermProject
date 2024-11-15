package payment;

import exceptions.payment.PaymentException;
import mvc.models.User;

public interface PaymentMethod {
    public void makePayment(User user, double amount) throws PaymentException;
    public void makeRefund(User user, double amount);
}
