package com.federico.facchin.ejercicio1y2.models.Entities.View;

import com.federico.facchin.ejercicio1y2.models.Entities.Card;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OperationDto {

    private Card card;
    private Double percentageFee;
    private Double amount;
    private Double chargedFee;
}
