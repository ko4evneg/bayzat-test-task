package com.bayzat.cryptotracker.web;

import com.bayzat.cryptotracker.exception.ResourceNotFoundException;
import com.bayzat.cryptotracker.exception.SecurityAppException;
import com.bayzat.cryptotracker.exception.WrongStateException;
import com.bayzat.cryptotracker.exception.to.ApplicationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception) {
        return new ResponseEntity<>(exception.getResourceId(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = SecurityAppException.class)
    public ResponseEntity<?> handleSecurityException(SecurityException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = WrongStateException.class)
    public ResponseEntity<?> handleByDefault(WrongStateException exception) {
        ApplicationError applicationError = new ApplicationError(HttpStatus.BAD_REQUEST, exception.getMessage(), new Date());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handleByDefault(Exception exception) {
        ApplicationError applicationError = new ApplicationError(HttpStatus.BAD_REQUEST, exception.getMessage(), new Date());
        return ResponseEntity.internalServerError().body(applicationError);
    }
}
