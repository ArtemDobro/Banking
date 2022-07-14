package com.andersenlab.rmtbanking.depositservice.repository;

import com.andersenlab.rmtbanking.depositservice.entity.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AgreementRepository extends JpaRepository<Agreement, UUID> {

    List<Agreement> getAgreementsByAccountClientIdAndAccountActive(UUID clientId, boolean active);
}
