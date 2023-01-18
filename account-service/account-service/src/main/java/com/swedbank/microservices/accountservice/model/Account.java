package com.swedbank.microservices.accountservice.model;

import com.swedbank.microservices.accountservice.enumconst.AccCurrency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acc_Id")
    private long accId;
    @Column(name = "account_number", length = 8)
    private String accountNumber;

    @Column(name = "currency_balance")
    private BigDecimal currencyBalance;
    @Enumerated(EnumType.STRING)
    @Column(name = "acc_currency")
    private AccCurrency accCurrency;


}
