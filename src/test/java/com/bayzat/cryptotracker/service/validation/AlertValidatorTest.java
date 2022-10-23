package com.bayzat.cryptotracker.service.validation;

import com.bayzat.cryptotracker.exception.ResourceNotFoundException;
import com.bayzat.cryptotracker.model.Alert;
import com.bayzat.cryptotracker.model.Currency;
import com.bayzat.cryptotracker.service.CurrencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;

class AlertValidatorTest {
    private AlertValidator alertValidator;
    private CurrencyService currencyService;

    @BeforeEach
    public void init() {
        currencyService = Mockito.mock(CurrencyService.class);
        alertValidator = new AlertValidator(currencyService);

        Mockito.when(currencyService.find(eq(10L))).thenReturn(new Currency());
        Mockito.when(currencyService.find(eq(20L))).thenThrow(new ResourceNotFoundException(20L));
    }

    @Test
    public void validCurrencyNotThrows() {
        Alert validAlert = getAlert(10L);

        assertDoesNotThrow(() -> alertValidator.validate(validAlert));
    }

    @Test
    public void invalidCurrencyThrows() {
        Alert invalidAlert = getAlert(20L);

        assertThrows(ResourceNotFoundException.class, () -> alertValidator.validate(invalidAlert));
    }

    private Alert getAlert(long currencyId) {
        Currency currency = new Currency();
        currency.setId(currencyId);
        Alert alert = new Alert();
        alert.setCurrency(currency);
        return alert;
    }
}