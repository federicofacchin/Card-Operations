package com.federico.facchin.ejercicio1y2.models.Entities.View;

import lombok.Builder;
import lombok.Getter;

import java.math.BigInteger;
import java.time.LocalDate;

@Builder
@Getter
public class CardDto {
    private BigInteger number;
    private LocalDate dueDate;
    private String name;
    private String surname;

}
