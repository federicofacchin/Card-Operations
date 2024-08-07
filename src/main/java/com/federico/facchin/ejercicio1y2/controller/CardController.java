package com.federico.facchin.ejercicio1y2.controller;

import com.federico.facchin.ejercicio1y2.models.Entities.Card;
import com.federico.facchin.ejercicio1y2.models.Entities.View.CardDto;
import com.federico.facchin.ejercicio1y2.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/api/card")
@Tag(name = "card controller", description = "todos los endpoints de tarjetas")
public class CardController {
    @Autowired
    private CardService cardService;


    @PostMapping
    @Operation(summary = "Crea una tarjeta",
            description= "devuelve HTTP Status 201 si fue creada correctamente, se realizan todas las validaciones como fecha," +
                    "numero valido(segun cada tarjeta), y se calcula el porcentaje de cobro y se persiste en al BBDD")
    public ResponseEntity<CardDto> createCard(@RequestBody Card card){
        return new ResponseEntity<>(cardService.createCard(card),HttpStatus.CREATED);
    }

    @GetMapping("/{number}")
    @Operation(summary = "obtiene una tarjeta por su numero",
            description= "devuelve HTTP Status 200 si fue encontrada, devuelve todos los datos de la tarjeta escondiendo datos sensibles como el CVV")
    public ResponseEntity<CardDto> getCardInfo(@PathVariable BigInteger number){
        return new ResponseEntity<>(cardService.getCardByNumber(number), HttpStatus.OK);
    }

    @PostMapping("/matchCards/{id}")
    @Operation(summary = "compara una nueva tarjeta con una ya existente y la devuelve si coinciden",
            description= "devuelve HTTP Status 200 si hay coincidencia de id," +
                    "pero aclara si algun campo no coincide ademas, devuelve la tarjeta sin datos sensibles ")
    public ResponseEntity<CardDto> matchCards(@PathVariable Long id,@RequestBody Card card){
        return new ResponseEntity<>(cardService.matchCards(id, card), HttpStatus.OK);
    }
}
