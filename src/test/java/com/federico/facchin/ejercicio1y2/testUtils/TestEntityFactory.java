package com.federico.facchin.ejercicio1y2.testUtils;

import com.federico.facchin.ejercicio1y2.models.Entities.Card;
import com.federico.facchin.ejercicio1y2.models.Entities.CardFactory;
import com.federico.facchin.ejercicio1y2.models.Entities.CardHolder;
import com.federico.facchin.ejercicio1y2.models.Entities.View.CardDto;
import com.federico.facchin.ejercicio1y2.models.Entities.impl.AmexCard;
import com.federico.facchin.ejercicio1y2.models.Entities.impl.NaraCard;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.time.LocalDate;

public class TestEntityFactory {


    public static Card getAmexCard(){
        return new AmexCard( BigInteger.valueOf(1234567890123456L), CardHolder.builder().name("nombre").surname("apellido").build()
                , 0, LocalDate.now().plusYears(3));

    }

    public static Card getNaraCard(){
        return new NaraCard( BigInteger.valueOf(1111567890123456L), CardHolder.builder().name("nombre").surname("apellido").build()
                , 123, LocalDate.now().plusYears(2));
    }

    public static CardDto getCardDto(){
        return CardDto.builder().number(BigInteger.valueOf(1234567890123456L)).dueDate(LocalDate.now().plusYears(3)).name("nombre").surname("apellido").build();
    }
}
