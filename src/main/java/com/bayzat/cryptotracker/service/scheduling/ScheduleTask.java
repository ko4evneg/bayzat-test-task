package com.bayzat.cryptotracker.service.scheduling;

import com.bayzat.cryptotracker.service.AlertThresholdHitsChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ScheduleTask {
    private final AlertThresholdHitsChecker alertThresholdHitsChecker;

    @Scheduled(fixedRateString = "${bayzat.scheduler.interval}")
    public void checkAlertsHits() {
        alertThresholdHitsChecker.checkAllAlertsHits();
    }
}
