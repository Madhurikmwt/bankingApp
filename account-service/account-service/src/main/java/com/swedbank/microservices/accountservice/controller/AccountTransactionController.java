package com.swedbank.microservices.accountservice.controller;


import com.swedbank.microservices.accountservice.exception.AccountNotFoundException;
import com.swedbank.microservices.accountservice.exception.ParameterValidationException;
import com.swedbank.microservices.accountservice.model.Account;
import com.swedbank.microservices.accountservice.service.AccountService;
import com.swedbank.microservices.accountservice.utils.DepositInput;
import com.swedbank.microservices.accountservice.utils.InputValidator;
import com.swedbank.microservices.accountservice.utils.WithdrawInput;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import static com.swedbank.microservices.accountservice.enumconst.messages.SUCCESS;


@RestController
@RequestMapping("/api/v1")
public class AccountTransactionController {


    @Autowired
    private RestTemplate restTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountTransactionController.class);

    @Autowired
    private AccountService accountService;


    @PostMapping(value = "/deposit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deposit(
            @Valid @RequestBody DepositInput depositInput) {
        LOGGER.debug("Triggered AccountTransactionController.depositInput");
        boolean depositAmountSuccessful = false;
        Account newAccount = new Account();
        if (InputValidator.isAccountNoValid(depositInput.getTargetAccountNo())) {

            depositAmountSuccessful = accountService.depositAmountToAccount(depositInput);

            if (depositAmountSuccessful) {
                return new ResponseEntity<>(SUCCESS, HttpStatus.OK);

            } else {
                throw new AccountNotFoundException();
            }
        } else {
           throw new ParameterValidationException();
        }
    }


    @GetMapping(value = "/accounts/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> checkAccountBalance(@PathVariable String accountId) {

        if (InputValidator.isSearchCriteriaValid(accountId)) {
            Account account = accountService.getAccount(accountId);

            if (account == null) {
                throw new AccountNotFoundException();
            } else {
                return new ResponseEntity<>(account, HttpStatus.OK);
            }
        } else {
            throw new ParameterValidationException();
        }
    }


    @PostMapping(value = "/newWithdraw",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> newWithdraw(
            @RequestBody WithdrawInput withdrawInput) {

        boolean isWithdrawSuccessful = false;

        if (InputValidator.isAccountNoValid(withdrawInput.getAccountNumber())) {
            isWithdrawSuccessful = accountService.withdrawAmountFromAccount(withdrawInput);

            if (isWithdrawSuccessful) {
                final String uri = "https://httpstat.us/200";
                String result = restTemplate.getForObject(uri, String.class);
                System.out.println("Success Response: " + result);
                return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
            } else {
                throw new AccountNotFoundException();

            }

        } else {
            throw new ParameterValidationException();
        }
    }
}




