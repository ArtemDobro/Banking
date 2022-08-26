package com.andersenlab.rmtbanking.depositservice.repository;

import com.andersenlab.rmtbanking.depositservice.entity.CardProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CardProductRepository extends JpaRepository<CardProduct, UUID> {

    @Query("from CardProduct c where c.active=:active")
    List<CardProduct> findAllCardProducts(boolean active);

    Optional<CardProduct> findCardProductById(Long idProduct);
}
