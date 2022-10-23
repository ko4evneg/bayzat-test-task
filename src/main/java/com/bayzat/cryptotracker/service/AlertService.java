package com.bayzat.cryptotracker.service;

import com.bayzat.cryptotracker.exception.WrongStateException;
import com.bayzat.cryptotracker.model.Alert;
import com.bayzat.cryptotracker.model.Role;
import com.bayzat.cryptotracker.model.to.AlertTo;
import com.bayzat.cryptotracker.repository.AlertRepository;
import com.bayzat.cryptotracker.service.validation.AlertValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

import static com.bayzat.cryptotracker.model.AlertStatus.CANCELLED;
import static com.bayzat.cryptotracker.model.AlertStatus.NEW;

@Service
@RequiredArgsConstructor
public class AlertService extends AbstractOwnedEntityCrudService<Alert, AlertTo> {
    private final AlertRepository alertRepository;
    private final AlertValidator alertValidator;

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
        return super.saveNew(alert);
    }

    @Override
    public List<Alert> findAllOwned() {
        return alertRepository.findAllByUser_Id(getActiveUser().getId());
    }

    @Override
    public Alert update(Alert updatedEntity, Long id) {
        return updateOwned(updatedEntity, id);
    }

    @Override
    public void delete(Long id) {
        deleteOwned(id);
    }

    public void cancelOwned(Long id) {
        Alert alert = findOwned(id);
        changeStatus(id, alert);
    }

    public void cancel(Long id) {
        Alert alert = find(id);
        changeStatus(id, alert);
    }

    private void changeStatus(Long id, Alert alert) {
        if (NEW.equals(alert.getStatus())) {
            alert.setStatus(CANCELLED);
            save(alert, id);
        } else {
            throw new WrongStateException("Alert must have a new state!");
        }
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
