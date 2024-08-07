package com.federico.facchin.ejercicio1y2.models.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@AllArgsConstructor
@Embeddable
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CardHolder {
    @Column(name = "nombre_propietario")
    private String name;
    @Column(name = "apellido_propietario")
    private String surname;
}
