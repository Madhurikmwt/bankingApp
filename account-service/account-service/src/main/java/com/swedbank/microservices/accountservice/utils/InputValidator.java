package com.swedbank.microservices.accountservice.utils;

import static com.swedbank.microservices.accountservice.enumconst.messages.ACCOUNT_NUMBER_PATTERN;

public class InputValidator {
    public static boolean isSearchCriteriaValid(AccountInput accountInput) {
        return ACCOUNT_NUMBER_PATTERN.matcher(accountInput.getAccountNumber()).find();
    }

    public static boolean isSearchCriteriaValid(String accountId) {
        return ACCOUNT_NUMBER_PATTERN.matcher(accountId).find();
    }

    public static boolean isAccountNoValid(String accountNo) {
        return ACCOUNT_NUMBER_PATTERN.matcher(accountNo).find();
    }

}
