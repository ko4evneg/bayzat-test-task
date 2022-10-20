package com.bayzat.cryptotracker.exception;

public class CryptoTrackerException extends RuntimeException{
    public CryptoTrackerException() {    }

    public CryptoTrackerException(String message) {
        super(message);
    }

    public CryptoTrackerException(String message, Throwable cause) {
        super(message, cause);
    }
}
