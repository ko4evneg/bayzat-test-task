package com.bayzat.cryptotracker.exception.to;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ApplicationError {
    private HttpStatus status;
    private String message;
    private Date timestamp;
}
