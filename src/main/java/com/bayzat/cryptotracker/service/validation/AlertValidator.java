package com.bayzat.cryptotracker.service.validation;

import com.bayzat.cryptotracker.model.Alert;
import com.bayzat.cryptotracker.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlertValidator {
    private final CurrencyService currencyService;

    public void validate(Alert alert) {
        currencyService.find(alert.getCurrency().getId());
    }
}
