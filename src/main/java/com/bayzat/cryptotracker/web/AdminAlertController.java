package com.bayzat.cryptotracker.web;

import com.bayzat.cryptotracker.model.Alert;
import com.bayzat.cryptotracker.model.to.AlertTo;
import com.bayzat.cryptotracker.service.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping(value = "api/v1/admin/alerts")
@RequiredArgsConstructor
public class AdminAlertController extends AbstractEntityController<Alert, AlertTo> {
    private final AlertService alertService;

    @PostConstruct
    public void init() {
        service = alertService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("{id}/cancel")
    public ResponseEntity<?> cancel(@PathVariable Long id) {
        alertService.cancel(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
