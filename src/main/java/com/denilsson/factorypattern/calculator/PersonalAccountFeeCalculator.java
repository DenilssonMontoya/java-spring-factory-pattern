package com.denilsson.factorypattern.calculator;

import com.denilsson.factorypattern.custom.AccountType;
import org.springframework.stereotype.Component;

@AccountType(value = "personal")
@Component
public class PersonalAccountFeeCalculator implements FeeCalculatorIF{

    @Override
    public Double calculateFee() {
        return 0.01;
    }
}
