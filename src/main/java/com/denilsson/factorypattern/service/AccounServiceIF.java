package com.denilsson.factorypattern.service;

import com.denilsson.factorypattern.model.Account;

import java.util.Optional;

public interface AccounServiceIF {

    String createAccount(Account account);

    Optional<Account> findById(String id);
}
