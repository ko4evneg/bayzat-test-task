package com.bayzat.cryptotracker.service;

import com.bayzat.cryptotracker.model.Alert;
import com.bayzat.cryptotracker.model.to.AlertTo;
import com.bayzat.cryptotracker.repository.AlertRepository;
import com.bayzat.cryptotracker.service.validation.AlertValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;

import static com.bayzat.cryptotracker.model.AlertStatus.NEW;

@Service
@RequiredArgsConstructor
public class AlertService extends AbstractOwnedEntityCrudService<Alert, AlertTo> {
    private final AlertRepository alertRepository;
    private final AlertValidator alertValidator;
    private final CurrencyService currencyService;

    @PostConstruct
    public void init() {
        repository = alertRepository;
    }

    @Override
    public Alert saveNew(Alert alert) {
        alertValidator.validate(alert);
        if (alert.getStatus() == null) {
            alert.setStatus(NEW);
        }
        alert.setThresholdEvaluationDirection();
        return super.saveNew(alert);
    }

    private BigDecimal getCurrencyCurrentPrice(Alert alert) {
        Long currencyId = alert.getCurrency().getId();
        return currencyService.find(currencyId).getCurrentPrice();
    }

    @Override
    public List<Alert> findAllOwned() {
        return alertRepository.findAllByUser_Id(getActiveUser().getId());
    }

    @Override
    public Alert update(Alert alert, Long id) {
        alert.setThresholdEvaluationDirection();
        return super.update(alert, id);
    }

    @Transactional
    public void cancelOwned(Long id) {
        findOwned(id).cancel();
    }

    @Transactional
    public void cancel(Long id) {
        find(id).cancel();
    }

    @Override
    public Class<AlertTo> getToType() {
        return AlertTo.class;
    }

    @Override
    public Class<Alert> getEntityType() {
        return Alert.class;
    }
}
