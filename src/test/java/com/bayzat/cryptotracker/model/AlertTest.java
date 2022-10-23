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
    public void directionSetFalse() {
        Alert alert = new Alert();
        alert.setTargetPrice(new BigDecimal("50"));
        alert.setCurrency(getCurrency("66"));
        alert.setThresholdEvaluationDirection();

        assertFalse(alert.isUpperDirection());
    }

    @Test
    public void directionSetTrue() {
        Alert alert = new Alert();
        alert.setTargetPrice(new BigDecimal("50"));
        alert.setCurrency(getCurrency("49.99999"));
        alert.setThresholdEvaluationDirection();

        assertTrue(alert.isUpperDirection());
    }

    @Test
    public void directionSetFalseOnBorderCase() {
        Alert alert = new Alert();
        alert.setTargetPrice(new BigDecimal("50"));
        alert.setCurrency(getCurrency("50"));
        alert.setThresholdEvaluationDirection();

        assertFalse(alert.isUpperDirection());
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

    @Test
    public void acknowledgeThrowOnWrongStatus() {
        Alert alert1 = new Alert();
        Alert alert2 = new Alert();
        Alert alert3 = new Alert();
        alert1.setStatus(AlertStatus.NEW);
        alert2.setStatus(AlertStatus.CANCELLED);
        alert3.setStatus(AlertStatus.ACKED);

        assertThrows(WrongStateException.class, alert1::acknowledge);
        assertThrows(WrongStateException.class, alert2::acknowledge);
        assertThrows(WrongStateException.class, alert3::acknowledge);
    }

    @Test
    public void acknowledgeSetSuccess() {
        Alert alert = new Alert();
        alert.setStatus(AlertStatus.TRIGGERED);

        alert.acknowledge();

        assertEquals(AlertStatus.ACKED, alert.getStatus());
    }

    @Test
    public void largerThresholdWasHit() {
        Alert alert = new Alert();
        alert.setCurrency(getCurrency("19"));
        alert.setTargetPrice(new BigDecimal("20.3"));
        alert.setThresholdEvaluationDirection();
        alert.setCurrency(getCurrency("20.3001"));

        assertTrue(alert.wasThresholdHit());
    }

    @Test
    public void smallerThresholdWasHit() {
        Alert alert = new Alert();
        alert.setCurrency(getCurrency("30"));
        alert.setTargetPrice(new BigDecimal("20.3"));
        alert.setThresholdEvaluationDirection();
        alert.setCurrency(getCurrency("20.2999"));

        assertTrue(alert.wasThresholdHit());
    }

    @Test
    public void noThresholdThrows() {
        Alert alert = new Alert();
        alert.setCurrency(getCurrency("30"));
        alert.setTargetPrice(new BigDecimal("20.3"));
        alert.setCurrency(getCurrency("20.2999"));

        assertThrows(NullPointerException.class, alert::wasThresholdHit);
    }

    private Currency getCurrency(String currentPrice) {
        Currency currency = new Currency();
        currency.setCurrentPrice(new BigDecimal(currentPrice));
        return currency;
    }
}
