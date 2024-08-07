package com.federico.facchin.ejercicio1y2.controller;


import com.federico.facchin.ejercicio1y2.models.Entities.Card;
import com.federico.facchin.ejercicio1y2.models.Entities.View.OperationDto;


import com.federico.facchin.ejercicio1y2.service.OperationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/operation")
@Tag(name = "operation controller", description = "todos los endpoints de operaciones")
public class OperationController {

    @Autowired
    OperationService operationService;


    @PostMapping
    @io.swagger.v3.oas.annotations.Operation(summary = "realiza una operacion",
            description= "devuelve HTTP Status 201 si fue realizada correctamente, se realiza una operacion validando" +
                    " tanto tarjeta como los monton de operacion, si la tarjeta existe en la BBDD utiliza el fee ya calculado" +
                    " ,de lo contrario calcula el fee y la registra")
    public ResponseEntity<OperationDto> makeTransaction(@RequestBody OperationDto operation){
        return new ResponseEntity<>(operationService.makeTransaction(operation), HttpStatus.CREATED);
    }

    @PostMapping("/operation-fee")
    @io.swagger.v3.oas.annotations.Operation(summary = "realiza una consulta de cargos de servicio con montos",
            description= "devuelve HTTP Status 200 si fue realizada correctamente," +
                    " realiza el calculo de cuanto se pagaria en caso de realizar una operacion exitosa")
    public ResponseEntity<String> checkFees(@RequestBody Card card, Double amount){
        return new ResponseEntity<>(operationService.checkFees(amount,card), HttpStatus.OK);
    }


}
