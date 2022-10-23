package com.bayzat.cryptotracker.model.to;

import com.bayzat.cryptotracker.model.AlertStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AlertTo extends BaseNamedTo {
    private Long currencyId;
    private BigDecimal targetPrice;
    private AlertStatus status;
}
