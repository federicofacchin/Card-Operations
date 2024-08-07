package com.federico.facchin.ejercicio1y2.exception;

public class CardNotFoundException extends RuntimeException{

    public CardNotFoundException(String message) {
        super(message);
    }

}
