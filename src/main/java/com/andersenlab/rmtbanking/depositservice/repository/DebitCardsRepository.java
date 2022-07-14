package com.andersenlab.rmtbanking.depositservice.repository;

import com.andersenlab.rmtbanking.depositservice.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DebitCardsRepository extends JpaRepository<Card, UUID> {
    List<Card> getCardsByAccountClientIdAndAccountActive(UUID clientId, boolean active);

}