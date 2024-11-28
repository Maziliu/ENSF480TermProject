package ENSF480TermProject.backend.enums;

import ENSF480TermProject.backend.models.CreditCard;
import ENSF480TermProject.backend.models.DebitCard;
import ENSF480TermProject.backend.models.PaymentCard;

public enum PaymentCardType {
    DEBIT(DebitCard.class),
    CREDIT(CreditCard.class);

    private final Class<? extends PaymentCard> cardClass;

    PaymentCardType(Class<? extends PaymentCard> cardClass){
        this.cardClass = cardClass;
    }    

    public Class<? extends PaymentCard> getCardClass(){
        return cardClass;
    }
}
