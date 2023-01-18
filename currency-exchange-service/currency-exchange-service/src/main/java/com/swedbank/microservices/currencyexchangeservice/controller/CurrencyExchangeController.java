package com.swedbank.microservices.currencyexchangeservice.controller;


import com.swedbank.microservices.currencyexchangeservice.dto.ExchangeResponse;
import com.swedbank.microservices.currencyexchangeservice.service.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1")
public class CurrencyExchangeController {


    @Autowired
    CurrencyExchangeService service;

    @GetMapping("/convert/from/{from}/to/{to}/quantity/{quantity}")
    public ExchangeResponse retrieveExchangeValue(@PathVariable String from, @PathVariable String to,
                                                  @PathVariable BigDecimal quantity) {
        ExchangeResponse exchangeResponse = service.getExchangeByFromAndTo(from,to, quantity);

       return exchangeResponse;
    }


}
