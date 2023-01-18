package com.swedbank.microservices.accountservice.utils;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    private int statusCode;

}