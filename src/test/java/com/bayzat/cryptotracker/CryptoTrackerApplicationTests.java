package com.bayzat.cryptotracker;

import com.bayzat.cryptotracker.model.Alert;
import com.bayzat.cryptotracker.model.AlertStatus;
import com.bayzat.cryptotracker.model.BaseEntity;
import com.bayzat.cryptotracker.model.Currency;
import com.bayzat.cryptotracker.model.Role;
import com.bayzat.cryptotracker.model.User;
import com.bayzat.cryptotracker.repository.AlertRepository;
import com.bayzat.cryptotracker.repository.CurrencyRepository;
import com.bayzat.cryptotracker.repository.RoleRepository;
import com.bayzat.cryptotracker.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CryptoTrackerApplicationTests {
    @Autowired
    private AlertRepository alertRepository;
    @Autowired
    private CurrencyRepository currencyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    void initialSqlScriptsMappedToModelSuccess() {
        Role expectedRole = new Role("TESTROLE");
        User expectedUser = new User("testUser", "pwd", Set.of(expectedRole));
        Currency expectedCurrency = new Currency("TestCoin", "TTC",
                new BigDecimal("123456789.123456".toCharArray()), true);
        Alert expectedAlert = new Alert("testAlert", expectedCurrency, expectedUser,
                new BigDecimal("200.500".toCharArray()), true, AlertStatus.NEW);

        saveAndUpdateId(expectedRole, roleRepository);
        saveAndUpdateId(expectedCurrency, currencyRepository);
        saveAndUpdateId(expectedUser, userRepository);
        saveAndUpdateId(expectedAlert, alertRepository);

        Alert actualAlert = getActualEntityFromRepository(expectedAlert, alertRepository);
        Currency actualCurrency = getActualEntityFromRepository(expectedCurrency, currencyRepository);
        User actualUser = getActualEntityFromRepository(expectedUser, userRepository);
        Role actualRole = getActualEntityFromRepository(expectedRole, roleRepository);

        assertEquals(expectedAlert, actualAlert);
        assertEquals(expectedCurrency, actualCurrency);
        assertEquals(expectedCurrency, actualAlert.getCurrency());
        assertEquals(expectedUser, actualUser);
        assertEquals(expectedUser, actualAlert.getUser());
        assertEquals(expectedRole, actualRole);
    }

    private <T extends BaseEntity> void saveAndUpdateId(T entity, CrudRepository<T, Long> repository) {
        Long roleId = repository.save(entity).getId();
        entity.setId(roleId);
    }

    private  <T extends BaseEntity> T getActualEntityFromRepository(T entity, CrudRepository<T, Long> repository) {
        return repository
                .findById(entity.getId())
                .orElseThrow(() -> new RuntimeException(entity.getClass().getSimpleName() + " was not retrieved!"));
    }
}
