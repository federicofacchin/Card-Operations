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

@Entity(name = "Nara")
@DiscriminatorValue("Nara")
@NoArgsConstructor
@JsonTypeName("NARA")
public class NaraCard extends Card {

    public NaraCard(BigInteger number, CardHolder cardHolder, int cvv, LocalDate dueDate) {
        super(number,cardHolder,cvv,dueDate);
    }

    @Override
    public Double calculatePercentageFee() {
        Double result = getDueDate().getDayOfMonth() * 0.5;
        return calculateRange(result);
    }

    public static Double calculatePercentageFee(CardCheckFeesDto cardCheckFeesDto){
        return cardCheckFeesDto.getMonth() * 0.5;
    }
}
