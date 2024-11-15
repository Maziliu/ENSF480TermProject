package payment.Transaction;

import payment.Transaction.enums.TransactionType;

public class Refund extends Transaction {
    private String transactionReferenceOfPurchase;
    private Purchase purchaseToRefund;

    public Refund(String transactionReferenceOfPurchase){
        this.transactionReferenceOfPurchase = transactionReferenceOfPurchase;
    }

    public String getTransactionReferenceOfPurchase() {
        return transactionReferenceOfPurchase;
    }

    public Purchase getPurchaseToRefund() {
        return purchaseToRefund;
    }

    public void setPurchaseToRefund(Purchase purchaseToRefund) {
        this.purchaseToRefund = purchaseToRefund;

        this.userEmail = purchaseToRefund.userEmail;
        this.userPaymentCard = purchaseToRefund.userPaymentCard;
        this.transactionAmount = purchaseToRefund.transactionAmount * 0.85;
        this.transactionType = TransactionType.REFUND;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "";
    }

    @Override
    public void setTransactionAmount(double transactionAmount) {
        super.setTransactionAmount(transactionAmount * 0.85);
    }
}
