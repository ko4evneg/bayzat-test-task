package com.bayzat.cryptotracker.service;

import com.bayzat.cryptotracker.model.Currency;
import com.bayzat.cryptotracker.model.to.CurrencyTo;
import com.bayzat.cryptotracker.repository.CurrencyRepository;
import com.bayzat.cryptotracker.service.validation.CurrencyValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CurrencyService extends AbstractCrudService<Currency, CurrencyTo> {
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

    @Override
    public Class<CurrencyTo> getToType() {
        return CurrencyTo.class;
    }

    @Override
    public Class<Currency> getEntityType() {
        return Currency.class;
    }
}
