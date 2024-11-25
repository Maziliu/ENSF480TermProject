package ENSF480TermProject.backend.interfaces;

import java.math.BigDecimal;
import java.util.Optional;

import ENSF480TermProject.backend.dtos.Transaction.PaymentDTO;
import ENSF480TermProject.backend.enums.TransactionType;
import ENSF480TermProject.backend.models.RegisteredUser;
import ENSF480TermProject.backend.models.Transaction;
import ENSF480TermProject.backend.repositories.RegisteredUserRepository;
import ENSF480TermProject.backend.repositories.TransactionRepository;

public class PurchaseTransactionStrategy implements TransactionStrategy {

    @Override
    public PaymentDTO proccessTransaction(Transaction transaction, TransactionRepository transactionRepository, RegisteredUserRepository registeredUserRepository) {
        if (transaction.getUserId() == null) {
            return new PaymentDTO(
                TransactionType.PURCHASE,
                transaction.getTransactionDateTime(),
                transaction.getTransactionAmount(),
                BigDecimal.ZERO, 
                transaction.getTransactionAmount(), 
                null, 
                transaction.getUserEmail()
            );
        }


        Optional<RegisteredUser> userOpt = registeredUserRepository.findById(transaction.getUserId());
        Optional<Integer> theaterCreditsOpt = registeredUserRepository.findTheatreCreditsById(transaction.getUserId());

        if (userOpt.isPresent() && theaterCreditsOpt.isPresent()) {
            RegisteredUser user = userOpt.get();
            int theaterCredits = theaterCreditsOpt.get();

            BigDecimal creditsAsBigDecimal = BigDecimal.valueOf(theaterCredits);
            BigDecimal transactionAmount = transaction.getTransactionAmount();

            BigDecimal paidByCredits;
            BigDecimal paidByUser;

            if (creditsAsBigDecimal.compareTo(transactionAmount) >= 0) {
                paidByCredits = transactionAmount;
                paidByUser = BigDecimal.ZERO;
                registeredUserRepository.subtractTheatreCredits(user.getUserId(), transactionAmount.intValue());

            } else {
                paidByCredits = creditsAsBigDecimal; 
                paidByUser = transactionAmount.subtract(creditsAsBigDecimal); 
                registeredUserRepository.subtractTheatreCredits(user.getUserId(), theaterCredits);
            }

            transactionRepository.save(transaction);

            return new PaymentDTO(
                TransactionType.PURCHASE,
                transaction.getTransactionDateTime(),
                transactionAmount,
                paidByCredits,
                paidByUser,
                transaction.getUserId(),
                user.getEmail()
            );
        }

        return null;
    }
}
