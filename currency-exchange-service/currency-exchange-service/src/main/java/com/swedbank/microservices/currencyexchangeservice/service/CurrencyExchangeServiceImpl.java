package com.swedbank.microservices.currencyexchangeservice.service;

import com.swedbank.microservices.currencyexchangeservice.dto.ExchangeResponse;
import com.swedbank.microservices.currencyexchangeservice.enumconst.AccCurrency;
import com.swedbank.microservices.currencyexchangeservice.model.CurrencyExchange;
import com.swedbank.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService{

    @Autowired
    CurrencyExchangeRepository repository;
    @Override
    public ExchangeResponse getExchangeByFromAndTo(String from, String to, BigDecimal quantity) {
        ExchangeResponse exchangeResponse = new ExchangeResponse();
        CurrencyExchange exchangeObj = repository.findByFromCRNCY(AccCurrency.valueOf(from));
        if(exchangeObj != null){
            exchangeResponse.setAccCurrency(AccCurrency.valueOf(to));
            exchangeResponse.setTotalAmount(quantity.multiply(exchangeObj.getConversionMultiple()));
        }
        return exchangeResponse;
    }
}
