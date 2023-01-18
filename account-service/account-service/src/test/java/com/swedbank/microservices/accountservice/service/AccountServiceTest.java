package com.swedbank.microservices.accountservice.service;

import com.swedbank.microservices.accountservice.enumconst.AccCurrency;
import com.swedbank.microservices.accountservice.model.Account;
import com.swedbank.microservices.accountservice.repository.AccountRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository repository;

    private Account account;

    @BeforeEach
    public void setUp(){
        account = Account.builder()
                .accId(1L)
                .accountNumber("22056788")
                .currencyBalance(BigDecimal.valueOf(100))
                .accCurrency(AccCurrency.EUR)
                .build();
    }

    @Test
    public void getAccountBasic(){
        Mockito.when(repository.findByAccountNumber("22056788"))
            .thenReturn(Optional.of(new Account(1, "22056788", BigDecimal.valueOf(100), AccCurrency.EUR)));

        Account account = accountService.getAccount("22056788");

        assertNotNull(account);
    }

    @Test
    public void get_existsByAccountNumberAndAccCurrency_Basic(){
        Mockito.when(repository.existsByAccountNumberAndAccCurrency("22056788",AccCurrency.EUR)).thenReturn(true);
        Boolean exists = accountService.existsByAccountNumberAndAccCurrency("22056788",AccCurrency.EUR);
        assertTrue(exists);
    }

    @Test
    public void get_existsByAccountNumberAndAccCurrency_false(){
        Mockito.when(repository.existsByAccountNumberAndAccCurrency("22056788",AccCurrency.EUR)).thenReturn(false);
        Boolean exists = accountService.existsByAccountNumberAndAccCurrency("22056788",AccCurrency.EUR);
        assertFalse(exists);
    }

    @Test
    public void get_findByAccountNumberAndAccCurrency_basic(){
        Mockito.when(repository.findByAccountNumberAndAccCurrency("22056788",AccCurrency.EUR))
                .thenReturn(new Account(1L, "22056788", BigDecimal.valueOf(100), AccCurrency.EUR));
        Account acc = accountService.findByAccountNumberAndAccCurrency("22056788",AccCurrency.EUR);
        assertNotNull(acc);
    }





}
