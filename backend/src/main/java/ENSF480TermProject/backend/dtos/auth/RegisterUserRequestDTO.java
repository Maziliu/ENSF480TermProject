package ENSF480TermProject.backend.dtos.auth;

import ENSF480TermProject.backend.dtos.user.PaymentCardDTO;

public class RegisterUserRequestDTO {
    private CredentialsDTO credentials;
    private PaymentCardDTO paymentCard;

    public RegisterUserRequestDTO() {}

    //Get
    public CredentialsDTO getCredentials() {
        return credentials;
    }

    public PaymentCardDTO getPaymentCard() {
        return paymentCard;
    }

    //Set
    public void setCredentials(CredentialsDTO credentials) {
        this.credentials = credentials;
    }

    public void setPaymentCard(PaymentCardDTO paymentCard) {
        this.paymentCard = paymentCard;
    }
}
