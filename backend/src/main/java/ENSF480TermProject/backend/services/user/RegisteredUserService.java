package ENSF480TermProject.backend.services.user;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ENSF480TermProject.backend.dtos.user.PaymentCardDTO;
import ENSF480TermProject.backend.dtos.user.UserDetailsDTO;
import ENSF480TermProject.backend.enums.PaymentCardType;
import ENSF480TermProject.backend.factories.PaymentCardFactory;
import ENSF480TermProject.backend.models.PaymentCard;
import ENSF480TermProject.backend.models.RegisteredUser;
import ENSF480TermProject.backend.repositories.PaymentCardRepository;
import ENSF480TermProject.backend.repositories.RegisteredUserRepository;
import jakarta.transaction.Transactional;

@Service
public class RegisteredUserService {
    private final RegisteredUserRepository registeredUserRepository;
    private final PaymentCardRepository paymentCardRepository;

    public RegisteredUserService(RegisteredUserRepository registeredUserRepository, PaymentCardRepository paymentCardRepository) {
        this.registeredUserRepository = registeredUserRepository;
        this.paymentCardRepository = paymentCardRepository;
    }

    public Optional<RegisteredUser> getUserDetails(Long userId){
        Optional<RegisteredUser> user = this.registeredUserRepository.findById(userId);
        return user;
    }


    public Optional<RegisteredUser> updateUserDetails(Long userId, UserDetailsDTO userDetailsDTO) {
        Optional<RegisteredUser> optionalUser = registeredUserRepository.findById(userId);
    
        if (optionalUser.isEmpty()) {
            return Optional.empty();
        }
    
        RegisteredUser user = optionalUser.get();
        user.setFirstName(userDetailsDTO.getFirst_name());
        user.setLastName(userDetailsDTO.getLast_name());
        user.setEmail(userDetailsDTO.getEmail());
        user.setAddress(userDetailsDTO.getAddress());
        
        for(PaymentCardDTO paymentCardDTO : userDetailsDTO.getPaymentCards()){
            updatePaymentCard(userId, paymentCardDTO);
        }

        return Optional.of(registeredUserRepository.save(user));
    }

    public Optional<String> deleteUser(Long userId){
        Optional<RegisteredUser> user = registeredUserRepository.findById(userId);
        if(user.isEmpty()){
            return Optional.empty();
        }

        registeredUserRepository.deleteById(userId);

        return Optional.of("User has been successfully deleted.");
    }

    public Optional<RegisteredUser> addPaymentCard(Long userId, PaymentCardDTO paymentCardDTO){
        PaymentCard paymentCard = PaymentCardFactory.createPaymentCard(paymentCardDTO.getPaymentCardType(), paymentCardDTO.getCardNumber(), paymentCardDTO.getCardHolderName(), paymentCardDTO.getCvv(), paymentCardDTO.getExpiryDate());
        Optional<RegisteredUser> user = registeredUserRepository.findById(userId);
    
        if (user.isEmpty()) {
            return Optional.empty();
        }
        
        paymentCard.setRegisteredUser(user.get());

        user.get().addPaymentCard(paymentCard);

        paymentCardRepository.save(paymentCard);
        
        return Optional.of(user.get());
    }

    public Optional<RegisteredUser> deletePaymentCard(Long userId, Long cardId){
        Optional<PaymentCard> paymentCard = paymentCardRepository.findById(cardId);
        if(paymentCard.isEmpty() || paymentCard.get().getRegisteredUser().getUserId() != userId){
            return Optional.of(null);
        }
        paymentCardRepository.deleteById(cardId);

        return registeredUserRepository.findById(userId);
    }

    private void updatePaymentCard(Long userId, PaymentCardDTO paymentCardDTO){
        Optional<PaymentCard> paymentCardOptional = paymentCardRepository.findById(paymentCardDTO.getCardId());
        if(paymentCardOptional.isEmpty()){
            return;
        }

        PaymentCard paymentCard = paymentCardOptional.get();
        paymentCard.setCardHolderName(paymentCardDTO.getCardHolderName());
        paymentCard.setCardNumber(paymentCardDTO.getCardNumber());
        paymentCard.setCvv(paymentCardDTO.getCvv());
        paymentCard.setExpireDate(paymentCardDTO.getExpiryDate());
        
        paymentCardRepository.save(paymentCard);
    }
    
}
