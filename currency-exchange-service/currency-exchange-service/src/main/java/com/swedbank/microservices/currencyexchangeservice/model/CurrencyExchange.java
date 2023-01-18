package com.swedbank.microservices.currencyexchangeservice.model;

import com.swedbank.microservices.currencyexchangeservice.enumconst.AccCurrency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "currency_exchange")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyExchange {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "from_")
        @Enumerated(EnumType.STRING)
        private AccCurrency fromCRNCY;
        @Column(name = "to_")
        @Enumerated(EnumType.STRING)
        private AccCurrency toCRNCY;
        @Column(name = "conversion_multiple")

        private BigDecimal conversionMultiple;

}
