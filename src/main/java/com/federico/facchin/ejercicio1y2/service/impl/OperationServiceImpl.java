package com.federico.facchin.ejercicio1y2.service.impl;

import com.federico.facchin.ejercicio1y2.exception.OperationNotValidException;
import com.federico.facchin.ejercicio1y2.models.Entities.Card;
import com.federico.facchin.ejercicio1y2.models.Entities.impl.Operation;
import com.federico.facchin.ejercicio1y2.models.Entities.View.OperationDto;
import com.federico.facchin.ejercicio1y2.repository.OperationRepository;
import com.federico.facchin.ejercicio1y2.service.CardService;
import com.federico.facchin.ejercicio1y2.service.OperationService;
import com.federico.facchin.ejercicio1y2.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OperationServiceImpl implements OperationService {

    private static final int MAX_OPERATION_AMOUNT = 1000;

    @Autowired
    private CardService cardService;


    @Autowired
    private OperationRepository operationRepository;

    @Override
    public OperationDto makeTransaction(OperationDto operationDto) {

        validateOperation(operationDto.getAmount());
        Optional<Card> card = cardService.findCardBynumber(operationDto.getCard().getNumber());
        Card operationCard;
        Operation operation;
        if(card.isEmpty()){
            operationCard = cardService.buildCard(operationDto.getCard());
            cardService.createCard(operationCard);

        }else{
            operationCard = card.get();
        }
        operation = buildOperation(operationDto.getAmount(), operationCard);
        setFeeAmount(operationCard,operation);
        operationRepository.save(operation);
        return buildOperationDto(operation);
    }

    private Double calculatePercentageFee(Card operationCard) {
        return operationCard.calculatePercentageFee();
    }

    private Operation buildOperation(Double amount, Card operationCard) {
        return Operation.builder().amount(amount).card(operationCard).build();
    }

    @Override
    public String checkFees(Double amount, Card card){
        validateOperation(amount);
        Card cardToBeChecked = cardService.buildCard(card);
        return "Tendras que pagar " + calculateFeeAmount(cardToBeChecked.calculatePercentageFee(),amount) + " al usar tu "
                + card.getClass().getSimpleName();
    }
    private OperationDto buildOperationDto(Operation operation) {
        return OperationDto.builder().amount(operation.getAmount()).chargedFee(operation.getChargedFee())
                .card(operation.getCard()).percentageFee(calculatePercentageFee(operation.getCard())).build();
    }

    private Double calculateFeeAmount(Double percentageFee, Double amount) {
        return percentageFee * amount;
    }
    private void setFeeAmount(Card operationCard, Operation operation) {
        operation.setChargedFee(calculateFeeAmount(operationCard.calculatePercentageFee(),operation.getAmount()));
    }


    private void validateOperation(Double amount) {
        if (amount > MAX_OPERATION_AMOUNT){
            throw new OperationNotValidException(Messages.OPERATION_NOT_VALID);
        }
    }
}
