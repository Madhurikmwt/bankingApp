package com.swedbank.microservices.currencyexchangeservice.repository;

import com.swedbank.microservices.currencyexchangeservice.enumconst.AccCurrency;
import com.swedbank.microservices.currencyexchangeservice.model.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange,Long> {

    public CurrencyExchange findByFromCRNCYAndToCRNCY(AccCurrency from, AccCurrency to);

    public CurrencyExchange findByFromCRNCY(AccCurrency from);

}
