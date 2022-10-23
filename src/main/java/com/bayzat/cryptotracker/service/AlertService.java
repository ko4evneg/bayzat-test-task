package com.bayzat.cryptotracker.service;

import com.bayzat.cryptotracker.model.Alert;
import com.bayzat.cryptotracker.model.AlertStatus;
import com.bayzat.cryptotracker.model.to.AlertTo;
import com.bayzat.cryptotracker.repository.AlertRepository;
import com.bayzat.cryptotracker.service.validation.AlertValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertService extends AbstractOwnedCrudService<Alert, AlertTo> {
    private final AlertRepository alertRepository;
    private final AlertValidator alertValidator;

    @PostConstruct
    public void init() {
        repository = alertRepository;
    }

    @Override
    public Alert saveNew(Alert alert) {
        alertValidator.validate(alert);
        alert.setUser(getActiveUser());
        alert.setStatus(AlertStatus.NEW);
        return super.saveNew(alert);
    }

    @Override
    public List<Alert> findAll() {
        return alertRepository.findAllByUser_Id(getActiveUser().getId());
    }

    @Override
    public Alert find(Long id) {
        return super.findOwned(id);
    }

    @Override
    public Alert update(Alert updatedEntity, Long id) {
        return super.updateOwned(updatedEntity, id);
    }

    @Override
    public void delete(Long id) {
        super.deleteOwned(id);
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
