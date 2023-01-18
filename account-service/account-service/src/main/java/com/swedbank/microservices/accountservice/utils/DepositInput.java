package com.swedbank.microservices.accountservice.utils;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@NoArgsConstructor
@Data
public class DepositInput {

    long accId;
    @NotBlank(message = "Target account no is mandatory")
    private String targetAccountNo;

    // Prevent fraudulent transfers attempting to abuse currency conversion errors
    @Positive(message = "Transfer amount must be positive")
    private BigDecimal amount;

    private String accCurrency;

    private long userId;

    @Override
    public String toString() {
        return "DepositInput{" +
                "targetAccountNo='" + targetAccountNo + '\'' +
                ", amount='" + amount + '\'' +
                ", currency='" + accCurrency + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepositInput that = (DepositInput) o;
        return Objects.equals(targetAccountNo, that.targetAccountNo) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(accCurrency, that.accCurrency) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetAccountNo, amount, accCurrency, userId);
    }
}
