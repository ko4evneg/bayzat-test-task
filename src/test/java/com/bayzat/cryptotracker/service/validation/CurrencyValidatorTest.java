package com.bayzat.cryptotracker.service.validation;

import com.bayzat.cryptotracker.exception.UnsupportedCurrencyCreationException;
import com.bayzat.cryptotracker.model.Currency;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyValidatorTest {
    private CurrencyValidator currencyValidator = new CurrencyValidator();

    @Test
    public void prohibitedSymbolFails() {
        assertThrows(UnsupportedCurrencyCreationException.class,
                () -> currencyValidator.validate(getCurrency("ETH")));
        assertThrows(UnsupportedCurrencyCreationException.class,
                () -> currencyValidator.validate(getCurrency("MRD")));
    }

    @Test
    public void allowedSymbolSuccess() {
        assertDoesNotThrow(() -> currencyValidator.validate(getCurrency("ABC")));
        assertDoesNotThrow(() -> currencyValidator.validate(getCurrency("BYN")));
    }

    private Currency getCurrency(String symbol) {
        Currency currency = new Currency();
        currency.setSymbol(symbol);
        return currency;
    }
}