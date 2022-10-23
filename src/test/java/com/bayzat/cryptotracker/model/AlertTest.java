package com.bayzat.cryptotracker.model;

import com.bayzat.cryptotracker.exception.WrongStateException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlertTest {

    @Test
    public void upperThresholdSetTrue() {
        Alert alert = new Alert();
        alert.setTargetPrice(new BigDecimal("50"));
        alert.calculateIfTargetIsMore(new BigDecimal("49.99999"));

        assertTrue(alert.getTargetIsMore());
    }

    @Test
    public void upperThresholdSetFalse() {
        Alert alert = new Alert();
        alert.setTargetPrice(new BigDecimal("49.99999"));
        alert.calculateIfTargetIsMore(new BigDecimal("50"));

        assertFalse(alert.getTargetIsMore());
    }

    @Test
    public void upperThresholdBorderCaseSetFalse() {
        Alert alert = new Alert();
        alert.setTargetPrice(new BigDecimal(50));
        alert.calculateIfTargetIsMore(new BigDecimal(50));

        assertFalse(alert.getTargetIsMore());
    }

    @Test
    public void cancelThrowOnWrongStatus() {
        Alert alert1 = new Alert();
        Alert alert2 = new Alert();
        Alert alert3 = new Alert();
        alert1.setStatus(AlertStatus.ACKED);
        alert2.setStatus(AlertStatus.CANCELLED);
        alert3.setStatus(AlertStatus.TRIGGERED);

        assertThrows(WrongStateException.class, alert1::cancel);
        assertThrows(WrongStateException.class, alert2::cancel);
        assertThrows(WrongStateException.class, alert3::cancel);
    }

    @Test
    public void cancelSetSuccess() {
        Alert alert = new Alert();
        alert.setStatus(AlertStatus.NEW);

        alert.cancel();

        assertEquals(AlertStatus.CANCELLED, alert.getStatus());
    }
}
