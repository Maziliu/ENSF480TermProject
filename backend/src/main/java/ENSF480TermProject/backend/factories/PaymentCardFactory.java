package ENSF480TermProject.backend.factories;

import ENSF480TermProject.backend.enums.PaymentCardType;
import ENSF480TermProject.backend.models.PaymentCard;

public class PaymentCardFactory {
    public static PaymentCard createPaymentCard(PaymentCardType paymentCardType, Object... args) {
        try {
            Class<?>[] paramTypes = new Class<?>[args.length];
            for (int i = 0; i < args.length; i++) {
                paramTypes[i] = args[i].getClass();
            }
            return paymentCardType.getCardClass().getConstructor(paramTypes).newInstance(args);
        } catch (Exception e) {
            throw new RuntimeException("Error creating PaymentCard of type: " + paymentCardType, e);
        }
    }
}
