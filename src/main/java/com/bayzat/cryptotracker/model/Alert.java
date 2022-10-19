package com.bayzat.cryptotracker.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name = "alerts")
public class Alert extends BaseNamedEntity {
    public Alert() {
    }

    public Alert(String name, Currency currency, User user, BigDecimal targetPrice, AlertStatus status) {
        super(name);
        this.currency = currency;
        this.user = user;
        this.targetPrice = targetPrice;
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

    @Enumerated(value = EnumType.STRING)
    private AlertStatus status;

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(BigDecimal targetPrice) {
        this.targetPrice = targetPrice;
    }

    public AlertStatus getStatus() {
        return status;
    }

    public void setStatus(AlertStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
