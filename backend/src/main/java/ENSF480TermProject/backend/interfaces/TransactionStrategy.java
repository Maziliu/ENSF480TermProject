package ENSF480TermProject.backend.interfaces;

import ENSF480TermProject.backend.dtos.transaction.TransactionResponseDTO;
import ENSF480TermProject.backend.enums.TransactionType;
import ENSF480TermProject.backend.models.Transaction;

public interface TransactionStrategy {
    public TransactionType getType();
    public TransactionResponseDTO processTransaction(Transaction transaction);
}
