package payment;

import mvc.models.User;

public interface PaymentMethod {
    void makePayment(User user, double amount);

    @Override
    String toString();
}
