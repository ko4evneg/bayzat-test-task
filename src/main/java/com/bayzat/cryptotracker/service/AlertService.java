package com.bayzat.cryptotracker.service;

import com.bayzat.cryptotracker.model.Alert;
import com.bayzat.cryptotracker.model.AlertStatus;
import com.bayzat.cryptotracker.model.Role;
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
    public static final Role ADMIN_ROLE = new Role("ADMIN");
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
        if (userIsAdmin()) {
            return super.findAll();
        }
        return findAllOwned();
    }

    private List<Alert> findAllOwned() {
        return alertRepository.findAllByUser_Id(getActiveUser().getId());
    }

    @Override
    public Alert find(Long id) {
        if (userIsAdmin()) {
            return super.find(id);
        }
        return super.findOwned(id);
    }

    @Override
    public Alert update(Alert updatedEntity, Long id) {
        if (userIsAdmin()) {
            return super.update(updatedEntity, id);
        }
        return super.updateOwned(updatedEntity, id);
    }

    @Override
    public void delete(Long id) {
        if (userIsAdmin()) {
            super.delete(id);
            return;
        }
        super.deleteOwned(id);
    }

    private boolean userIsAdmin() {
        return getActiveUser().getRoles().contains(ADMIN_ROLE);
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
