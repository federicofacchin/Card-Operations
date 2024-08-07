package com.federico.facchin.ejercicio1y2.models.Entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.federico.facchin.ejercicio1y2.models.Entities.impl.AmexCard;
import com.federico.facchin.ejercicio1y2.models.Entities.impl.NaraCard;
import com.federico.facchin.ejercicio1y2.models.Entities.impl.VisaCard;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigInteger;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "CARD_BRAND")
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AmexCard.class, name = "AMEX"),
        @JsonSubTypes.Type(value = NaraCard.class, name = "NARA"),
        @JsonSubTypes.Type(value = VisaCard.class, name = "VISA"),
})
public abstract class Card {
    public static final int standardNumberLenght = 16 ;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "card_id")
    private Long id;


    @NotNull
    @Positive
    @Column(name = "numero")
    private BigInteger number;

    @NotNull
    @Column(name = "fecha_vencimiento")
    private LocalDate dueDate;

    @Positive
    @Column(name = "codigo_verificacion")
    private int cvv;

    @Embedded
    private CardHolder cardHolder;

    public Card(BigInteger number, CardHolder cardHolder, int cvv, LocalDate dueDate) {
        this.cardHolder = cardHolder;
        this.cvv = cvv;
        this.dueDate = dueDate;
        this.number = number;
    }


    public abstract Double calculatePercentageFee();
    public double calculateRange(Double percentage){
        if (percentage > 5.0)
            return 5.0;
        if (percentage < 0.3)
            return 0.3;
        return percentage;
    }


}
