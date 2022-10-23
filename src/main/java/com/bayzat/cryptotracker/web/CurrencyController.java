package com.bayzat.cryptotracker.web;

import com.bayzat.cryptotracker.model.Currency;
import com.bayzat.cryptotracker.model.to.CurrencyTo;
import com.bayzat.cryptotracker.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping(value = "api/v1/currencies")
@RequiredArgsConstructor
public class CurrencyController extends AbstractEntityController<Currency, CurrencyTo> {
    private final CurrencyService currencyService;

    @PostConstruct
    public void init() {
        service = currencyService;
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> save(@RequestBody CurrencyTo to, @PathVariable Long id) {
        return super.save(to, id);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> saveNew(@RequestBody CurrencyTo to) {
        return super.saveNew(to);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return super.delete(id);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> update(@RequestBody CurrencyTo to, @PathVariable Long id) {
        return super.update(to, id);
    }
}

