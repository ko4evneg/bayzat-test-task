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

import static com.bayzat.cryptotracker.model.AlertStatus.CANCELLED;
import static com.bayzat.cryptotracker.model.AlertStatus.NEW;

@Entity
@Table(name = "alerts")
@Getter
@Setter
@NoArgsConstructor
public class Alert extends BaseNamedEntity implements OwnedEntity {
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

    @Setter(AccessLevel.NONE)
    @Column(name = "target_is_more")
    private Boolean targetIsMore;

    @Enumerated(value = EnumType.STRING)
    private AlertStatus status;

    public void calculateIfTargetIsMore(BigDecimal currentPrice) {
        //todo: equals edge-case should be discussed with business analyst
        targetIsMore = targetPrice.compareTo(currentPrice) > 0;
    }

    public void cancel() {
        if (NEW.equals(status)) {
            status = CANCELLED;
        } else {
            throw new WrongStateException("Alert must have a new state!");
        }
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
