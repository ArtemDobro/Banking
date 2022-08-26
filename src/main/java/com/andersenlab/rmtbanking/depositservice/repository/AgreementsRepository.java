package com.andersenlab.rmtbanking.depositservice.repository;

import com.andersenlab.rmtbanking.depositservice.entity.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AgreementsRepository extends JpaRepository<Agreement, UUID> {
    /**
     * searching for agreement by account id which(account id) comes to us from front
     * @param accountId - receiving from front
     */
    Optional<Agreement> findByAccountId(UUID accountId);
}
