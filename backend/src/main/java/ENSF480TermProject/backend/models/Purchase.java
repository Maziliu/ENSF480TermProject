package ENSF480TermProject.backend.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import ENSF480TermProject.backend.enums.TransactionType;
import ENSF480TermProject.backend.interfaces.PurchaseTransactionStrategy;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Purchase extends Transaction {
    @Column(name = "item_description")
    private String itemDescription;

    public Purchase(BigDecimal transactionAmount, LocalDateTime transactionDate, Long userId, String userEmail, String... itemDescription) {
        super(transactionAmount, transactionDate, new PurchaseTransactionStrategy(), userId, userEmail);

        StringBuilder descriptionBuilder = new StringBuilder();
        for (String description : itemDescription) {
            descriptionBuilder.append(description).append(" ");
        }
        this.itemDescription = descriptionBuilder.toString().trim();
    }

    public Purchase() { }

    //Get
    public String getItemDescription() {
        return itemDescription;
    }

    //Set
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }
}