package com.swedbank.microservices.accountservice.service;

import com.swedbank.microservices.accountservice.enumconst.AccCurrency;
import com.swedbank.microservices.accountservice.model.Account;
import com.swedbank.microservices.accountservice.repository.AccountRepository;
import com.swedbank.microservices.accountservice.utils.DepositInput;
import com.swedbank.microservices.accountservice.utils.WithdrawInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account getAccount(String accountNumber) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        return account.orElse(null);
    }

    @Override
    public boolean withdrawAmountFromAccount(WithdrawInput withdrawInput) {
        Account newAccount = new Account();
        if(existsByAccountNumberAndAccCurrency(
                withdrawInput.getAccountNumber(),AccCurrency.valueOf(withdrawInput.getAccCurrency()))) {
            newAccount = findByAccountNumberAndAccCurrency(
                    withdrawInput.getAccountNumber(), AccCurrency.valueOf(withdrawInput.getAccCurrency()));

            boolean isSufficientAmount = isAmountAvailable(withdrawInput.getAmount(),newAccount.getCurrencyBalance());

            if(isSufficientAmount) {
                newAccount.setCurrencyBalance(newAccount.getCurrencyBalance().subtract(withdrawInput.getAmount()));
            }else{
                return false;
            }
            //To do before debiting, simulate a call to external system (ie. external logging)
            // by calling some web page (like https://httpstat.us/)

            updateAccountBalance(newAccount);
            return true;
        }else{
            return false;
        }
    }


    @Override
    public boolean depositAmountToAccount(DepositInput depositInput) {
        Account newAccount = new Account();
        if(existsByAccountNumberAndAccCurrency(
                depositInput.getTargetAccountNo(),AccCurrency.valueOf(depositInput.getAccCurrency()))) {
            newAccount = findByAccountNumberAndAccCurrency(
                    depositInput.getTargetAccountNo(), AccCurrency.valueOf(depositInput.getAccCurrency()));
            newAccount.setCurrencyBalance(newAccount.getCurrencyBalance().add(depositInput.getAmount()));
            updateAccountBalance(newAccount);
            return true;
        }else if(existsByAccountNumber(depositInput.getTargetAccountNo())) {
            newAccount.setAccountNumber(depositInput.getTargetAccountNo());
            newAccount.setAccCurrency(AccCurrency.valueOf(depositInput.getAccCurrency()));
            newAccount.setCurrencyBalance(depositInput.getAmount());
            updateAccountBalance(newAccount);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void updateAccountBalance(Account account) {
        accountRepository.save(account);
    }

    @Override
    public boolean isAmountAvailable(BigDecimal amount, BigDecimal accountBalance) {
        return (accountBalance.compareTo(amount) > 0);
    }

    @Override
    public boolean existsByAccountNumberAndAccCurrency(String accountNo,AccCurrency accCurrency){
        boolean isExist = accountRepository.existsByAccountNumberAndAccCurrency(accountNo,accCurrency);
        return isExist;
    }

    @Override
    public Account findByAccountNumberAndAccCurrency(String accountNo,AccCurrency accCurrency){
        Account account = accountRepository.findByAccountNumberAndAccCurrency(accountNo,accCurrency);
        return account;
    }


    @Override
    public boolean existsByAccountNumber(String accountNo){
        boolean isExist = accountRepository.existsByAccountNumber(accountNo);
        return isExist;
    }

}
