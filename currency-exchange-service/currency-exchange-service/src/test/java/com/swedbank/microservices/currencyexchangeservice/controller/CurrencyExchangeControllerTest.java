package com.swedbank.microservices.currencyexchangeservice.controller;

import com.swedbank.microservices.currencyexchangeservice.service.CurrencyExchangeServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CurrencyExchangeController.class)
class CurrencyExchangeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @Mock
    CurrencyExchangeServiceImpl service;

    @InjectMocks
    CurrencyExchangeController controller;

    @Test
    public void convertValidInputReturnsConvertedAmountInJson() throws Exception {
        mockMvc.perform(get("/api/v1/convert/from/{from}/to/{to}/quantity/{quantity}")
                        .param("from", "USD")
                        .param("to", "EUR")
                        .param("quantity", "100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accCurrency", is("EUR")))
                .andExpect(jsonPath("$.totalAmount", is(92.22)))
                .andReturn();
    }

}