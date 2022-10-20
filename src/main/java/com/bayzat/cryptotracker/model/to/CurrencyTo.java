package com.bayzat.cryptotracker.model.to;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CurrencyTo extends BaseNamedTo{
    private String symbol;
    private BigDecimal currentPrice;
    private Boolean enabled;
}
