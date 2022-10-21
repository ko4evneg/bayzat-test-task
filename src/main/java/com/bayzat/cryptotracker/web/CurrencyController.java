package com.bayzat.cryptotracker.web;

import com.bayzat.cryptotracker.model.Currency;
import com.bayzat.cryptotracker.model.to.CurrencyTo;
import com.bayzat.cryptotracker.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping(value = "api/v1/currencies")
@RequiredArgsConstructor
public class CurrencyController extends EntityController<Currency, CurrencyTo> {
    private final CurrencyService currencyService;

    @PostConstruct
    public void init() {
        service = currencyService;
    }
}

