package com.swedbank.microservices.accountservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swedbank.microservices.accountservice.enumconst.AccCurrency;
import com.swedbank.microservices.accountservice.model.Account;
import com.swedbank.microservices.accountservice.repository.AccountRepository;
import com.swedbank.microservices.accountservice.service.AccountServiceImpl;
import com.swedbank.microservices.accountservice.utils.AccountInput;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;


@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc
@DataJpaTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@RunWith(MockitoJUnitRunner.class)
public class AccountTransactionControllerTest {

    private static MockHttpServletRequest request;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    AccountInput accountInput;
    @Mock
    AccountServiceImpl accountServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private JdbcTemplate jdbc;

    @Mock
    private AccountRepository accountRepository;
    @Autowired
    Account accountMock;

    @Autowired
    @InjectMocks
    private AccountTransactionController controller;

    public static final String APPLICATION_JSON_UTF8 = MediaType.APPLICATION_JSON_VALUE;

    @Before
    public void setupMockMvc(){
        accountServiceMock = new AccountServiceImpl();
        controller = new AccountTransactionController();

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        accountMock = new Account();
        accountMock.setAccId(12345L);
        accountMock.setAccountNumber("22056789");
        accountMock.setCurrencyBalance(BigDecimal.valueOf(100));
        accountMock.setAccCurrency(AccCurrency.EUR);

        //entityManager.persist(accountMock);
    }

    @BeforeAll
    public static void setup(){
        request = new MockHttpServletRequest();
    }

    @Test
    public void testSetup(){

    }


   /* @Test
    public void testCheckBalance() throws Exception{
        //AccountRepository ar  = mock(AccountRepository.class);
        Account account = new Account(12345L, "22056789",BigDecimal.valueOf(100),AccCurrency.EUR);

      *//*  Mockito.when(accountRepository.findByAccountNumber("22056788"))
                .thenReturn(Optional.of(new Account(1, "22056788", BigDecimal.valueOf(100), AccCurrency.EUR)));
*//*
        Mockito.when(accountServiceMock.getAccount("22056788")).thenReturn(account);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/accounts/22056788")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.accId").value(12345))
                .andExpect(jsonPath("$.accountNumber").value("22056789"))
                .andExpect(jsonPath("$.currencyBalance").value(100));
    }
*/




}
