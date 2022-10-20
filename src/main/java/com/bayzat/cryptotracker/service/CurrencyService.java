package com.bayzat.cryptotracker.service;

import com.bayzat.cryptotracker.model.Currency;
import com.bayzat.cryptotracker.repository.CurrencyRepository;
import com.bayzat.cryptotracker.service.validation.CurrencyValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CurrencyService extends AbstractCrudService<Currency> {
    private final CurrencyRepository currencyRepository;
    private final CurrencyValidator validator;

    @PostConstruct
    public void init() {
        repository = currencyRepository;
    }

    @Override
    public Currency saveNew(Currency currency) {
        validator.validate(currency);
        return super.saveNew(currency);
    }
}
