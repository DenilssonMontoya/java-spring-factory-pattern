package com.denilsson.factorypattern.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Object> handleAccountNotFoundException(
            AccountNotFoundException ex
    ){
        Map<String, Object> body =  new HashMap<>();
        body.put("message", ex.getMessage());
        body.put("trace", ex.getStackTrace());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FeeCalcNotImplementedException.class)
    public ResponseEntity<Object> handleFeeCalcNotImplementedException(
            FeeCalcNotImplementedException ex
    ){
        Map<String, Object> body =  new HashMap<>();
        body.put("message", ex.getMessage());
        body.put("trace", ex.getStackTrace());

        return new ResponseEntity<>(body, HttpStatus.NOT_IMPLEMENTED);
    }

}
