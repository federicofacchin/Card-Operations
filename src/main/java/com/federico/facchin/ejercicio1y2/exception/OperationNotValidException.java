package com.federico.facchin.ejercicio1y2.exception;

public class OperationNotValidException extends RuntimeException{
    public OperationNotValidException(String message) {
        super(message);
    }

}
