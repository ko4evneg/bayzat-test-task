package com.bayzat.cryptotracker.service;

import com.bayzat.cryptotracker.model.AlertStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AlertThresholdHitsChecker {
    private final AlertService alertService;
    private final NotificationService notificationService;

    //considering split all alerts processing to batches, depending on alerts count in app
    @Transactional
    public void checkAllAlertsHits() {
        alertService.findAll().forEach(alert -> {
            if (!alert.isAcknowledged() && alert.wasThresholdHit()) {
                notificationService.notify(alert.getUser().getId(), alert.getCurrency().getSymbol() + " threshold");
                alert.setStatus(AlertStatus.TRIGGERED);
            }
        });
    }
}
