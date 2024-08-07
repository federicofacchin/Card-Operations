package com.federico.facchin.ejercicio1y2.service;

import com.federico.facchin.ejercicio1y2.models.Entities.Card;
import com.federico.facchin.ejercicio1y2.models.Entities.View.OperationDto;


public interface OperationService {

    OperationDto makeTransaction(OperationDto operation);


    String checkFees(Double amount, Card card);
}
