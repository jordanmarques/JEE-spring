package com.example.account.exception;

public class AccountEmptyException extends RuntimeException {

    public AccountEmptyException(String message) {
        super(message);
    }
}
