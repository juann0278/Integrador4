package org.example.microserviciotarifa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Cobro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;
    private float monto;

    @ManyToOne
    @JoinColumn (name = "tarifa_id")
    private Tarifa tarifa;

}
