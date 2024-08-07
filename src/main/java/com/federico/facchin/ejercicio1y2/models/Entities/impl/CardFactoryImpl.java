package com.federico.facchin.ejercicio1y2.models.Entities.impl;

import com.federico.facchin.ejercicio1y2.exception.CardNotFoundException;
import com.federico.facchin.ejercicio1y2.models.Entities.Card;
import com.federico.facchin.ejercicio1y2.models.Entities.CardFactory;
import org.springframework.stereotype.Component;


@Component
public class CardFactoryImpl implements CardFactory {
    public static final String AMEX =  "AmexCard";
    public static final String VISA =  "VisaCard";
    public static final String NARA =  "NaraCard";
    @Override
    public Card create(Card card) {
        switch(card.getClass().getSimpleName()){
            case  AMEX -> {
                return new AmexCard(card.getNumber(),card.getCardHolder(),card.getCvv(),card.getDueDate());
            }
            case VISA -> {
                return new VisaCard(card.getNumber(),card.getCardHolder(),card.getCvv(),card.getDueDate());

            }
            case NARA -> {
                return new NaraCard(card.getNumber(),card.getCardHolder(),card.getCvv(),card.getDueDate());

            }
            default -> throw new CardNotFoundException("Tipo de tarjeta no soportado");


        }
    }
}
