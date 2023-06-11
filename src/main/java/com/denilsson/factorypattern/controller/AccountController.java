package com.denilsson.factorypattern.controller;

import com.denilsson.factorypattern.calculator.FeeCalculatorIF;
import com.denilsson.factorypattern.calculator.factory.FeeCalculatorFactory;
import com.denilsson.factorypattern.exception.AccountNotFoundException;
import com.denilsson.factorypattern.exception.FeeCalcNotImplementedException;
import com.denilsson.factorypattern.model.Account;
import com.denilsson.factorypattern.service.AccounServiceIF;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/account/v1")
@RequiredArgsConstructor
public class AccountController {


    private final AccounServiceIF accountControllerService;
    private final FeeCalculatorFactory feeCalculatorFactory;

    @PostMapping(value = "/create")
    public ResponseEntity<String> createAccount(@RequestBody Account account) {
        String accountId = accountControllerService.createAccount(account);
        return ResponseEntity.ok(accountId);
    }

    @GetMapping(value = "/calculate-fee/{accountId}")
    public ResponseEntity<Double> calculateFee(@PathVariable String accountId){
        Optional<Account> account = accountControllerService.findById(accountId);

        if(account.isEmpty())
            throw new AccountNotFoundException(accountId);

        Optional<FeeCalculatorIF> feeCalculatorIF = feeCalculatorFactory.getFeeCalculator(account.get().getAccountType());

        if(feeCalculatorIF.isEmpty())
            throw new  FeeCalcNotImplementedException(account.get().getAccountType());

        return new ResponseEntity<>(feeCalculatorIF.get().calculateFee(), HttpStatus.OK);
    }
}
