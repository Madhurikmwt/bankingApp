package com.swedbank.microservices.accountservice.repository;

import com.swedbank.microservices.accountservice.enumconst.AccCurrency;
import com.swedbank.microservices.accountservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    Optional<Account> findByAccountNumber(String accountNumber);
    boolean existsByAccountNumber(String accountNumber);

    Account findByAccountNumberAndAccCurrency(String accountNumber,AccCurrency accCurrency);

    boolean existsByAccountNumberAndAccCurrency(String accountNumber,AccCurrency accCurrency);
    boolean existsByAccCurrency(AccCurrency accCurrency);

}
