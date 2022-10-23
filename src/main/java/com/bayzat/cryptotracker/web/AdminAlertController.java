package com.bayzat.cryptotracker.web;

import com.bayzat.cryptotracker.model.Alert;
import com.bayzat.cryptotracker.model.to.AlertTo;
import com.bayzat.cryptotracker.service.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return super.findAll();
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {
        return super.find(id);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<?> save(@RequestBody AlertTo to, @PathVariable Long id) {
        return super.save(to, id);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("{id}")
    public ResponseEntity<?> update(@RequestBody AlertTo to, @PathVariable Long id) {
        return super.update(to, id);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return super.delete(id);
    }
}
