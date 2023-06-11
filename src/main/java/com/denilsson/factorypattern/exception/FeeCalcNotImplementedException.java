package com.denilsson.factorypattern.exception;

public class FeeCalcNotImplementedException extends RuntimeException {
    public FeeCalcNotImplementedException(String accountType) {
        super(String.format("Fee calculator for account type '%s' not implemented.", accountType));
    }
}
