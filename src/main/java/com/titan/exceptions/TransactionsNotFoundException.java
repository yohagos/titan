package com.titan.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TransactionsNotFoundException extends RuntimeException {

    public TransactionsNotFoundException(String message) {
        super(message);
    }
}
