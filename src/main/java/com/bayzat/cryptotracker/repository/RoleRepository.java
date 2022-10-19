package com.bayzat.cryptotracker.repository;

import com.bayzat.cryptotracker.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
