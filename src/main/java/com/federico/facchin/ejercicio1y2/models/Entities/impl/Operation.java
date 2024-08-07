package com.federico.facchin.ejercicio1y2.models.Entities.impl;

import com.federico.facchin.ejercicio1y2.models.Entities.Card;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotNull
    @JoinColumn(name="tarjeta_id")
    @OneToOne
    private Card card;
    @Column(name = "monto")
    private Double amount;

    @Column(name = "monto_cobrado")
    private Double chargedFee;
}
