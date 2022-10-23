package com.bayzat.cryptotracker.exception;

public class WrongStateException extends CryptoTrackerException {
    public WrongStateException(String message) {
        super(message);
    }
}
