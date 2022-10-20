package com.bayzat.cryptotracker.service;

import com.bayzat.cryptotracker.exception.ResourceNotFoundException;
import com.bayzat.cryptotracker.model.Currency;
import com.bayzat.cryptotracker.repository.CurrencyRepository;
import com.bayzat.cryptotracker.service.validation.CurrencyValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepository currencyRepository;
    private final ModelMapper mapper;
    private final CurrencyValidator validator;

    @Override
    public List<Currency> findAll() {
        return currencyRepository.findAll();
    }

    @Override
    public Currency find(Long id) {
        return currencyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public Currency saveNew(Currency currency) {
        validator.validate(currency);
        return currencyRepository.save(currency);
    }

    @Override
    public Currency save(Currency currency, Long id) {
        findIfExists(id);
        currency.setId(id);
        currency.setCreatedAt(new Date());
        return saveNew(currency);
    }

    @Override
    public Currency update(Currency updatedCurrency, Long id) {
        Currency currency = findIfExists(id);
        mapper.map(updatedCurrency, currency);
        return currencyRepository.save(currency);
    }

    @Override
    public void delete(Long id) {
        findIfExists(id);
        currencyRepository.deleteById(id);
    }

    private Currency findIfExists(Long id) {
        return currencyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
