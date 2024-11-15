package payment;

import exceptions.payment.IncorrectPaymentInformationException;
import mvc.models.User;

public class CreditPaymentMethod implements PaymentMethod {

    @Override
    public void makePayment(User user, double amount) throws IncorrectPaymentInformationException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'makePayment'");
    }

    @Override
    public void makeRefund(User user, double amount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'makeRefund'");
    }
}
