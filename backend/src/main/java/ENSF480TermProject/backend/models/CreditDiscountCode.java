package ENSF480TermProject.backend.models;

import java.time.LocalDateTime;
import java.util.UUID;

import ENSF480TermProject.backend.enums.CreditDiscountCodeStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CreditDiscountCodes")
public class CreditDiscountCode {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "code", nullable = false, unique = true, updatable = false)
    private UUID code;

    @Enumerated(EnumType.STRING)
    @Column(name = "code_status", nullable = false)
    private CreditDiscountCodeStatus codeStatus;

    @Column(name = "credit_amount", nullable = false)
    private Integer creditAmount;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "expire_date", nullable = false)
    private LocalDateTime expireDate;

    public CreditDiscountCode() {}

    public CreditDiscountCode(int creditAmount) {
        this.creditAmount = creditAmount;
        this.creationDate = LocalDateTime.now();
        this.expireDate = creationDate.plusYears(1);
    }

    //Get
    public UUID getCode() {
        return code;
    }

    public CreditDiscountCodeStatus getCodeStatus() {
        return codeStatus;
    }

    public Integer getCreditAmount() {
        return creditAmount;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    //Set
    public void setCode(UUID code) {
        this.code = code;
    }

    public void setCodeStatus(CreditDiscountCodeStatus codeStatus) {
        this.codeStatus = codeStatus;
    }

    public void setCreditAmount(Integer creditAmount) {
        this.creditAmount = creditAmount;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }
}
