package com.bayzat.cryptotracker.repository;

import com.bayzat.cryptotracker.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findAllByUser_Id(Long userId);
}
