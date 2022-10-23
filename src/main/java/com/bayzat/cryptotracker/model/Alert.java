package com.bayzat.cryptotracker.model;

import com.bayzat.cryptotracker.exception.WrongStateException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;

import static com.bayzat.cryptotracker.model.AlertStatus.ACKED;
import static com.bayzat.cryptotracker.model.AlertStatus.CANCELLED;
import static com.bayzat.cryptotracker.model.AlertStatus.NEW;
import static com.bayzat.cryptotracker.model.AlertStatus.TRIGGERED;

@Entity
@Table(name = "alerts")
@Getter
@Setter
@NoArgsConstructor
public class Alert extends BaseNamedEntity implements OwnedEntity {
    public Alert(String name, Currency currency, User user, BigDecimal targetPrice, Boolean upperDirection, AlertStatus status) {
        super(name);
        this.currency = currency;
        this.user = user;
        this.targetPrice = targetPrice;
        this.upperDirection = upperDirection;
        this.status = status;
    }

    @OneToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "target_price")
    private BigDecimal targetPrice;

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    @Column(name = "target_is_more")
    private Boolean upperDirection;

    @Enumerated(value = EnumType.STRING)
    private AlertStatus status;

    public void setThresholdEvaluationDirection() {
        //todo: equals edge-case should be discussed with business analyst
        upperDirection = targetPrice.compareTo(currency.getCurrentPrice()) > 0;
    }

    public Boolean isUpperDirection() {
        return upperDirection;
    }

    public boolean wasThresholdHit() {
        BigDecimal currentPrice = currency.getCurrentPrice();

        //todo: equals edge-case should be discussed with business analyst
        return TRIGGERED.equals(status) ||
                currentPrice.compareTo(targetPrice) >= 0 && upperDirection ||
                currentPrice.compareTo(targetPrice) < 0 && !upperDirection;
    }

    public void cancel() {
        if (NEW.equals(status)) {
            status = CANCELLED;
        } else {
            throw new WrongStateException("Alert must have a new state!");
        }
    }

    public void acknowledge() {
        if (TRIGGERED.equals(status)) {
            status = ACKED;
        } else {
            throw new WrongStateException("Alert must have a triggered state!");
        }
    }

    public boolean isAcknowledged() {
        return ACKED.equals(status);
    }

    public boolean isCancelled() {
        return CANCELLED.equals(status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Alert alert = (Alert) o;
        return Objects.equals(currency, alert.currency) && Objects.equals(user, alert.user) && targetPrice.compareTo(alert.targetPrice) == 0 && status == alert.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), currency, user, targetPrice, status);
    }
}
