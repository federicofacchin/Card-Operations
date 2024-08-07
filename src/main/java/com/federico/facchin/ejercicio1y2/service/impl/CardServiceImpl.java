package com.federico.facchin.ejercicio1y2.service.impl;

import com.federico.facchin.ejercicio1y2.exception.CardAlreadyExistsException;
import com.federico.facchin.ejercicio1y2.exception.CardLenghtNotValidException;
import com.federico.facchin.ejercicio1y2.exception.CardNotFoundException;
import com.federico.facchin.ejercicio1y2.exception.DueDateException;
import com.federico.facchin.ejercicio1y2.models.Entities.Card;
import com.federico.facchin.ejercicio1y2.models.Entities.CardFactory;
import com.federico.facchin.ejercicio1y2.models.Entities.View.CardDto;
import com.federico.facchin.ejercicio1y2.repository.CardRepository;
import com.federico.facchin.ejercicio1y2.service.CardService;
import com.federico.facchin.ejercicio1y2.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private CardFactory cardFactory;

    @Override
    public CardDto createCard(Card card) {
        validateCard(card);
        existsCardByNumber(card);
        return buildCardDto(cardRepository.save(card));
    }


    @Override
    public CardDto getCardByNumber(BigInteger number) {
        return buildCardDto(findCardBynumber(number).orElseThrow(() -> new CardNotFoundException(Messages.CARD_NOT_FOUND_BY_NUMBER)));
    }

    @Override
    public CardDto matchCards(Long id, Card card) {
        Card cardFound = findCardById(id);
        String message = "";

        if(!card.getClass().getSimpleName().equalsIgnoreCase(cardFound.getClass().getSimpleName())){
            message += Messages.BRAND_UNMATCHED;
        }
        if(cardFound.getCvv() != (card.getCvv())){
            message += Messages.CVV_UNMATCHED;
        }
        if(!Objects.equals(cardFound.getNumber(), card.getNumber())){
            message += Messages.NUMBER_UNMATCHED;
        }
        if(!cardFound.getDueDate().equals(card.getDueDate())){
            message += Messages.DUE_DATE_UNMATCHED;
        }

        if(!message.isBlank()){
           throw new CardNotFoundException(message);
        }
        return buildCardDto(cardFound);
    }

    private Card findCardById(Long id) {
        return cardRepository.findById(id).orElseThrow(() -> new CardNotFoundException(Messages.CARD_NOT_FOUND_BY_ID));
    }

    public boolean existsCardById(Long id) {
        return cardRepository.existsById(id);
    }

    @Override
    public Optional<Card> findCardBynumber(BigInteger number) {
        return cardRepository.findCardByNumber(number);
    }

    @Override
    public CardDto buildCardDto(Card card) {
        return CardDto.builder().dueDate(card.getDueDate()).number(card.getNumber())
                .name(card.getCardHolder().getName()).surname(card.getCardHolder().getSurname()).build();
    }

    @Override
    public Double calculatePercentageFee(Card card) {
        return card.calculatePercentageFee();
    }

    @Override
    public Card buildCard(Card card) {
        return cardFactory.create(card);
    }


    public void validateCard(Card card) {
        validateDueDate(card);
        validateCardNumber(card);
    }

    private void existsCardByNumber(Card card) {
        if(cardRepository.findCardByNumber(card.getNumber()).isPresent()){
             throw new CardAlreadyExistsException(Messages.CARD_ALREADY_EXISTS);
        }
    }

    private void validateDueDate(Card card) {
       if(LocalDate.now().isAfter(card.getDueDate())){
           throw new DueDateException(Messages.EXPIRED_CARD);
       }
    }

    private void validateCardNumber(Card card) {

        if(Card.standardNumberLenght != String.valueOf(card.getNumber()).length()){
            throw new CardLenghtNotValidException(Messages.INVALID_CARD_LENGHT);
        }
    }


}
