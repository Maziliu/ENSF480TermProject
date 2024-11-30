package ENSF480TermProject.backend.services.auth;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ENSF480TermProject.backend.dtos.auth.CredentialsDTO;
import ENSF480TermProject.backend.dtos.auth.RegisterUserRequestDTO;
import ENSF480TermProject.backend.factories.PaymentCardFactory;
import ENSF480TermProject.backend.models.PaymentCard;
import ENSF480TermProject.backend.models.RegisteredUser;
import ENSF480TermProject.backend.repositories.RegisteredUserRepository;

@Service
public class Authenticator {
    private final RegisteredUserRepository userRepository;

    public Authenticator(RegisteredUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public RegisteredUser registerUser(RegisterUserRequestDTO registerUserRequestDTO){
        RegisteredUser user = new RegisteredUser(registerUserRequestDTO.getCredentials().getEmail(), registerUserRequestDTO.getCredentials().getPassword());

        String cardNumber = registerUserRequestDTO.getPaymentCard().getCardNumber();
        String holderName = registerUserRequestDTO.getPaymentCard().getCardHolderName();
        String cvv = registerUserRequestDTO.getPaymentCard().getCvv();
        YearMonth expiryDate = registerUserRequestDTO.getPaymentCard().getExpiryDate();

        PaymentCard paymentCard = PaymentCardFactory.createPaymentCard(registerUserRequestDTO.getPaymentCard().getPaymentCardType(), cardNumber, holderName, cvv, expiryDate);
        paymentCard.setRegisteredUser(user);
        user.addPaymentCard(paymentCard);
        return userRepository.save(user);
    }

    public Optional<RegisteredUser> authenticateUser(CredentialsDTO credentialsDTO) {
        Optional<RegisteredUser> registeredUser = userRepository.findByEmail(credentialsDTO.getEmail());

        if (registeredUser.isPresent() && credentialsDTO.getPassword().equals(registeredUser.get().getPassword())) {
            return registeredUser;
        }

        return Optional.empty();
    }

}
