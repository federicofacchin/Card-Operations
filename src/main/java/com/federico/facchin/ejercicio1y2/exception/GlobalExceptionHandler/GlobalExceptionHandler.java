package com.federico.facchin.ejercicio1y2.exception.GlobalExceptionHandler;

import com.federico.facchin.ejercicio1y2.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CardLenghtNotValidException.class)
    public ResponseEntity<String> CardLenghtNotValidException(CardLenghtNotValidException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<String> CardNotFoundException(CardNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CardAlreadyExistsException.class)
    public ResponseEntity<String> CardAlreadyExistsException(CardAlreadyExistsException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(DueDateException.class)
    public ResponseEntity<String> DueDateException(DueDateException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OperationNotValidException.class)
    public ResponseEntity<String> OperationNotValidException(OperationNotValidException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
