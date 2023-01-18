package com.swedbank.microservices.currencyexchangeservice.service;

import com.swedbank.microservices.currencyexchangeservice.dto.ExchangeResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface CurrencyExchangeService {

   public ExchangeResponse getExchangeByFromAndTo(String from, String to , BigDecimal quantity);
}
