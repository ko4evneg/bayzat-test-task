package com.bayzat.cryptotracker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "currencies")
public class Currency extends BaseNamedEntity {
    public Currency() {
    }

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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

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
