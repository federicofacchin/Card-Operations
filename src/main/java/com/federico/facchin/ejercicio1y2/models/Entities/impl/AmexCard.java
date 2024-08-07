package com.federico.facchin.ejercicio1y2.models.Entities.impl;


import com.fasterxml.jackson.annotation.JsonTypeName;
import com.federico.facchin.ejercicio1y2.models.Entities.Card;
import com.federico.facchin.ejercicio1y2.models.Entities.CardHolder;
import com.federico.facchin.ejercicio1y2.models.Entities.View.CardCheckFeesDto;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;

@Entity(name = "Amex")
@DiscriminatorValue("Amex")
@NoArgsConstructor
@JsonTypeName("AMEX")
public class AmexCard extends Card {
    public AmexCard(BigInteger number, CardHolder cardHolder, int cvv, LocalDate dueDate) {
        super(number,cardHolder,cvv,dueDate);
    }

    @Override
    public Double calculatePercentageFee() {
        Double result = getDueDate().getMonth().getValue() * 0.1;
        return calculateRange(result);
    }

    public static Double calculatePercentageFee(CardCheckFeesDto cardCheckFeesDto){
        return cardCheckFeesDto.getMonth() * 0.1;
    }

}
