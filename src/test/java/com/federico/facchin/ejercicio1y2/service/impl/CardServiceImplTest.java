package com.federico.facchin.ejercicio1y2.service.impl;

import com.federico.facchin.ejercicio1y2.exception.CardAlreadyExistsException;
import com.federico.facchin.ejercicio1y2.exception.CardLenghtNotValidException;
import com.federico.facchin.ejercicio1y2.exception.CardNotFoundException;
import com.federico.facchin.ejercicio1y2.exception.DueDateException;
import com.federico.facchin.ejercicio1y2.models.Entities.Card;
import com.federico.facchin.ejercicio1y2.models.Entities.View.CardDto;
import com.federico.facchin.ejercicio1y2.repository.CardRepository;
import com.federico.facchin.ejercicio1y2.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Optional;

import static com.federico.facchin.ejercicio1y2.testUtils.TestEntityFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CardServiceImplTest {

    @Mock
    CardRepository cardRepository;

    @Mock
    CardService cardService;
    @InjectMocks
    CardServiceImpl cardServiceImpl;

    private CardDto expectedCardDto;
    private Card card;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        expectedCardDto = getCardDto();
        card = getAmexCard();
    }

    @Test
    void createCardOk(){
        when(cardRepository.save(any(Card.class))).thenReturn(card);
        CardDto cardDto = cardServiceImpl.createCard(card);
        assertEquals(expectedCardDto.getNumber(), cardDto.getNumber());
        assertEquals(expectedCardDto.getDueDate(), cardDto.getDueDate());
        assertEquals(expectedCardDto.getName(), cardDto.getName());
        assertEquals(expectedCardDto.getSurname(), cardDto.getSurname());
    }

    @Test
    void createCardNumberException(){

        card.setNumber(BigInteger.valueOf(123456789012345679L));
        assertThrows(CardLenghtNotValidException.class ,() -> cardServiceImpl.createCard(card));

    }

    @Test
    void createCardDateException(){

        card.setDueDate(LocalDate.now().minusMonths(1));
        assertThrows(DueDateException.class ,() -> cardServiceImpl.createCard(card));

    }
    @Test
    void createCardExistentNumberException(){

        when(cardRepository.findCardByNumber(card.getNumber())).thenReturn(Optional.of(card));
        assertThrows(CardAlreadyExistsException.class ,() -> cardServiceImpl.createCard(card));

    }

    @Test
    void matchCardsAllErrors(){

        when(cardRepository.findById(card.getId())).thenReturn(Optional.of(getNaraCard()));
        assertThrows(CardNotFoundException.class, () -> cardServiceImpl.matchCards(card.getId(), card));
    }

    @Test
    void matchCardsOk(){
        when(cardRepository.findById(card.getId())).thenReturn(Optional.ofNullable(card));
        CardDto cardDto = cardServiceImpl.matchCards(card.getId(), card);
        assertEquals(cardDto.getNumber(), card.getNumber());
        assertEquals(cardDto.getDueDate(), card.getDueDate());
        assertEquals(cardDto.getName(), card.getCardHolder().getName());
        assertEquals(cardDto.getSurname(), card.getCardHolder().getSurname());

    }
}
