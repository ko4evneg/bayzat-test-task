package com.bayzat.cryptotracker.service;

import com.bayzat.cryptotracker.model.Currency;

import java.util.List;

public interface CurrencyService {
    List<Currency> findAll();

    Currency find(Long id);

    Currency saveNew(Currency currency);

    Currency save(Currency currency, Long id);

    Currency update(Currency currency, Long id);

    void delete(Long id);
}
