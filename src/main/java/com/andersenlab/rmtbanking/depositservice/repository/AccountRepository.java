package com.andersenlab.rmtbanking.depositservice.repository;

import com.andersenlab.rmtbanking.depositservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByClientId(UUID clientId);
}