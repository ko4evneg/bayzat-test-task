package com.bayzat.cryptotracker.repository;

import com.bayzat.cryptotracker.model.Currency;
import com.bayzat.cryptotracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
