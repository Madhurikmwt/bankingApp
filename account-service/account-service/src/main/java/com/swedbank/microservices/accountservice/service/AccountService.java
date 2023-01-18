package com.swedbank.microservices.accountservice.service;

import com.swedbank.microservices.accountservice.enumconst.AccCurrency;
import com.swedbank.microservices.accountservice.model.Account;
import com.swedbank.microservices.accountservice.utils.DepositInput;
import com.swedbank.microservices.accountservice.utils.WithdrawInput;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface AccountService {

    public Account getAccount(String accountNumber);

    public boolean withdrawAmountFromAccount(WithdrawInput withdrawInput);

    public boolean depositAmountToAccount(DepositInput depositInput);
    public void updateAccountBalance(Account account);
    public boolean isAmountAvailable(BigDecimal amount, BigDecimal accountBalance);

    public boolean existsByAccountNumberAndAccCurrency(String accountNo, AccCurrency accCurrency);

    public Account findByAccountNumberAndAccCurrency(String accountNo,AccCurrency accCurrency);

    public boolean existsByAccountNumber(String accountNo);

}
