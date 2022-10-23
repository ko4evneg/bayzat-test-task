package com.bayzat.cryptotracker.web;

import com.bayzat.cryptotracker.model.Alert;
import com.bayzat.cryptotracker.model.to.AlertTo;
import com.bayzat.cryptotracker.service.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping(value = "api/v1/alerts")
@RequiredArgsConstructor
public class AlertController extends AbstractOwnedEntityController<Alert, AlertTo>{
    private final AlertService alertService;

    @PostConstruct
    public void init() {
        service = alertService;
        ownedEntityCrudService = alertService;
    }

    @PatchMapping("{id}/cancel")
    public ResponseEntity<?> cancel(@PathVariable Long id) {
        alertService.cancelOwned(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PatchMapping("{id}/acknowledge")
    public ResponseEntity<?> acknowledge(@PathVariable Long id) {
        alertService.acknowledgeOwned(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
