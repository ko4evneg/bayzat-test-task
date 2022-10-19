package com.bayzat.cryptotracker.repository;

import com.bayzat.cryptotracker.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert, Long> {
}
