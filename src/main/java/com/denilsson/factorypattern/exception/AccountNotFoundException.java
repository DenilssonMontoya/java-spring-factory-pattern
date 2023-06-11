package com.denilsson.factorypattern.exception;

public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException(String accountId) {
        super(String.format("Account Id %s not found.", accountId));
    }
}
