package com.federico.facchin.ejercicio1y2.repository;

import com.federico.facchin.ejercicio1y2.models.Entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findCardByNumber(BigInteger number);
}
