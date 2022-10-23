package com.bayzat.cryptotracker;

import com.bayzat.cryptotracker.config.ApplicationConfig;
import com.bayzat.cryptotracker.model.Alert;
import com.bayzat.cryptotracker.model.Currency;
import com.bayzat.cryptotracker.model.to.AlertTo;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ModelMapperLearningTest {
    private final ApplicationConfig applicationConfig = new ApplicationConfig();
    private final ModelMapper mapper = applicationConfig.modelMapper();

    @Test
    public void idNestedFieldMappingTest() {
        Currency currency = new Currency();
        currency.setId(10L);
        Alert expectedAlert = new Alert();
        expectedAlert.setCurrency(currency);

        AlertTo alertTo = new AlertTo();
        alertTo.setCurrencyId(10L);

        Alert actualAlert = mapper.map(alertTo, Alert.class);

        assertEquals(expectedAlert.getCurrency().getId(), actualAlert.getCurrency().getId());
        assertNull(actualAlert.getId());
    }
}
