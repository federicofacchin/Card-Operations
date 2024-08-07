package com.federico.facchin.ejercicio1y2.exception;

public class CardAlreadyExistsException extends RuntimeException{
    public CardAlreadyExistsException(String message) {
        super(message);
    }

}
