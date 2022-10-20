package com.bayzat.cryptotracker.web;

import com.bayzat.cryptotracker.model.Currency;
import com.bayzat.cryptotracker.model.to.CurrencyTo;
import com.bayzat.cryptotracker.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/v1/currencies")
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyService currencyService;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(mapFromEntity(currencyService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {
        return getOkResponse(currencyService.find(id));
    }

    @PostMapping
    public ResponseEntity<?> saveNew(@RequestBody CurrencyTo currencyTo) {
        Currency savedCurrency = currencyService.saveNew(mapToEntity(currencyTo));
        return getOkResponse(savedCurrency);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> save(@RequestBody CurrencyTo currencyTo, @PathVariable Long id) {
        Currency savedCurrency = currencyService.save(mapToEntity(currencyTo), id);
        return getOkResponse(savedCurrency);
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> update(@RequestBody CurrencyTo currencyTo, @PathVariable Long id) {
        Currency savedCurrency = currencyService.update(mapToEntity(currencyTo), id);
        return getOkResponse(savedCurrency);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        currencyService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    private ResponseEntity<CurrencyTo> getOkResponse(Currency savedCurrency) {
        return ResponseEntity.ok(mapFromEntity(savedCurrency));
    }

    private List<CurrencyTo> mapFromEntity(Collection<Currency> currencyToCollection) {
        return currencyToCollection.stream()
                .map(this::mapFromEntity)
                .toList();
    }

    private Currency mapToEntity(CurrencyTo currencyTo) {
        return mapper.map(currencyTo, Currency.class);
    }

    private CurrencyTo mapFromEntity(Currency currency) {
        return mapper.map(currency, CurrencyTo.class);
    }
}

