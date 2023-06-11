package com.denilsson.factorypattern.calculator.factory;


import com.denilsson.factorypattern.calculator.FeeCalculatorIF;
import com.denilsson.factorypattern.custom.AccountType;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class FeeCalculatorFactory {

    private final ApplicationContext applicationContext;

    public FeeCalculatorFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Optional<FeeCalculatorIF> getFeeCalculator(String accountType){

        Map<String, Object> feeCalculatorBeans = applicationContext.getBeansWithAnnotation(AccountType.class);

        return feeCalculatorBeans.entrySet()
                .stream()
                .filter(entry -> {
                    AccountType annotation = entry.getValue().getClass().getAnnotation(AccountType.class);
                    return annotation.value().equals(accountType);
                })
                .map(entry -> (FeeCalculatorIF)entry.getValue())
                .findFirst();
    }
    
}
