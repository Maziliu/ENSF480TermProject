package ENSF480TermProject.backend.strategies;


import java.util.Optional;

import org.springframework.stereotype.Component;

import ENSF480TermProject.backend.dtos.transaction.SubscriptionResponseDTO;
import ENSF480TermProject.backend.dtos.transaction.TransactionResponseDTO;
import ENSF480TermProject.backend.enums.TransactionType;
import ENSF480TermProject.backend.interfaces.TransactionStrategy;
import ENSF480TermProject.backend.models.RegisteredUser;
import ENSF480TermProject.backend.models.SubscriptionRenewal;
import ENSF480TermProject.backend.models.Transaction;
import ENSF480TermProject.backend.repositories.RegisteredUserRepository;
import ENSF480TermProject.backend.repositories.TransactionRepository;

@Component
public class SubscriptionTransactionStrategy implements TransactionStrategy{
    private final TransactionRepository transactionRepository;
    private final RegisteredUserRepository registeredUserRepository;

    public SubscriptionTransactionStrategy(TransactionRepository transactionRepository, RegisteredUserRepository registeredUserRepository) {
        this.transactionRepository = transactionRepository;
        this.registeredUserRepository = registeredUserRepository;
    }

    @Override
    public TransactionType getType() {
       return TransactionType.SUBSCRIPTION_RENEWAL;
    }

    @Override
    public TransactionResponseDTO processTransaction(Transaction transaction) {
        SubscriptionRenewal subscriptionTransaction = (SubscriptionRenewal) transaction;

        Optional<RegisteredUser> userOpt = registeredUserRepository.findById(subscriptionTransaction.getUserId());
        if(userOpt.isEmpty()){
            //Exception
        }

        transactionRepository.save(subscriptionTransaction);

        return new SubscriptionResponseDTO(subscriptionTransaction, userOpt.get());
    }
    
}
