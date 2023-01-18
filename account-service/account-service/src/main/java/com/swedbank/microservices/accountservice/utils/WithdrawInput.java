package com.swedbank.microservices.accountservice.utils;

import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

@Data
public class WithdrawInput extends AccountInput{
    String accountNumber;

    // Prevent fraudulent transfers attempting to abuse currency conversion errors
    @Positive(message = "Transfer amount must be positive")
    private BigDecimal amount;

    private String accCurrency;

    public WithdrawInput() {
        this.accountNumber = super.getAccountNumber();
    }

    @Override
    public String toString() {
        return "AccountInput{" +
                "accountNumber='" + accountNumber + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WithdrawInput that = (WithdrawInput) o;
        return Objects.equals(accountNumber, that.accountNumber) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, amount);
    }
}

