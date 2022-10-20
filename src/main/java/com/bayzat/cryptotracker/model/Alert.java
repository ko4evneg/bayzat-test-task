package com.bayzat.cryptotracker.model;

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

@Entity
@Table(name = "alerts")
@Getter
@Setter
@NoArgsConstructor
public class Alert extends BaseNamedEntity {
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
