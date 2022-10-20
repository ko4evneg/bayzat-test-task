package com.bayzat.cryptotracker.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "currencies")
@Getter
@Setter
@NoArgsConstructor
public class Currency extends BaseNamedEntity {
    public Currency(String name, String symbol, BigDecimal currentPrice, boolean enabled) {
        super(name);
        this.symbol = symbol;
        this.currentPrice = currentPrice;
        this.enabled = enabled;
    }

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "current_price")
    private BigDecimal currentPrice;

    @Column(name = "enabled")
    private boolean enabled;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Currency currency = (Currency) o;
        return symbol.equals(currency.symbol) && enabled == currency.enabled && currentPrice.compareTo(currency.currentPrice) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), symbol, currentPrice, enabled);
    }
}
