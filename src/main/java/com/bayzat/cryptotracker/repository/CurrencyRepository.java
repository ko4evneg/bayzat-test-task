package com.bayzat.cryptotracker.repository;

import com.bayzat.cryptotracker.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
