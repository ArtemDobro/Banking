package com.andersenlab.rmtbanking.depositservice.repository;

import com.andersenlab.rmtbanking.depositservice.entity.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.List;
import java.util.UUID;

/**
 * @author Bogdan Yakovenko
 */
@Repository
public interface AgreementRepository extends JpaRepository<Agreement, UUID> {
    /**
     * searching for agreement by account id which(account id) comes to us from front
     * @param accountId - receiving from front
     */
    Optional<Agreement> findByAccountId(UUID accountId);

    /**
     * Return list of {@link Agreement} of account with specific client id.
     *
     * @param clientId {@link UUID} client id
     * @param active, true if account is active and false if account is not
     * @return List of {@link Agreement}
     */
    @Query("from Agreement a where a.account.clientId =:clientId and a.account.active =:active")
    List<Agreement> getAgreementsByClientIdAndAccountStatus(UUID clientId, boolean active);
}
