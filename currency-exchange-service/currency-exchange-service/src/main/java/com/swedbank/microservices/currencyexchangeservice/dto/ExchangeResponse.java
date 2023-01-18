package com.swedbank.microservices.currencyexchangeservice.dto;

import com.swedbank.microservices.currencyexchangeservice.enumconst.AccCurrency;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExchangeResponse {
    private BigDecimal totalAmount;
   private AccCurrency accCurrency;
}
