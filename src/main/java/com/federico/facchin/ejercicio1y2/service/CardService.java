package com.federico.facchin.ejercicio1y2.service;

import com.federico.facchin.ejercicio1y2.models.Entities.Card;
import com.federico.facchin.ejercicio1y2.models.Entities.View.CardDto;

import java.math.BigInteger;
import java.util.Optional;

public interface CardService {
    CardDto createCard(Card card);

    CardDto getCardByNumber(BigInteger number);

    CardDto matchCards(Long id, Card card);

    void validateCard(Card card);

    public boolean existsCardById(Long id);

    Optional<Card> findCardBynumber(BigInteger number);

    CardDto buildCardDto(Card card);

    Double calculatePercentageFee(Card card);

    Card buildCard(Card card);
}
