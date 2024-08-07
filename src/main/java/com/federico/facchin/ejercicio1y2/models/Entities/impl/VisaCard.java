package com.federico.facchin.ejercicio1y2.models.Entities.impl;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.federico.facchin.ejercicio1y2.models.Entities.Card;
import com.federico.facchin.ejercicio1y2.models.Entities.CardHolder;
import com.federico.facchin.ejercicio1y2.models.Entities.View.CardCheckFeesDto;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;

@Entity(name = "Visa")
@DiscriminatorValue("Visa")
@NoArgsConstructor
@JsonTypeName("VISA")
public class VisaCard extends Card {

    public VisaCard(BigInteger number, CardHolder cardHolder, int cvv, LocalDate dueDate) {
        super(number,cardHolder,cvv,dueDate);
    }
    @Override
    public Double calculatePercentageFee() {
        return calculateRange((double) getDueDate().getYear() / (double) getDueDate().getMonthValue());
    }

    public static Double calculatePercentageFee(CardCheckFeesDto cardCheckFeesDto){
        return cardCheckFeesDto.getYear() / cardCheckFeesDto.getMonth();
    }
}
