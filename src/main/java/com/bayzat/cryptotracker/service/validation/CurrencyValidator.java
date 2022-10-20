package com.bayzat.cryptotracker.service.validation;

import com.bayzat.cryptotracker.exception.UnsupportedCurrencyCreationException;
import com.bayzat.cryptotracker.model.Currency;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CurrencyValidator {
    //todo: extract hardcoded to database settings
    private static final List<String> PROHIBITED_COINS = List.of("ETH", "LTC", "ZKN", "MRD", "LPR", "GBZ");

    public void validate(Currency currency) {
        String symbol = currency.getSymbol();
        if (PROHIBITED_COINS.contains(symbol)) {
            throw new UnsupportedCurrencyCreationException("Coin " + symbol + " is prohibited!");
        }
    }
}
