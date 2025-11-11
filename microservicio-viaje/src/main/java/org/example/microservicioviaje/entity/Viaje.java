package org.example.microservicioviaje.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idMonopatin;
    private Long idUsuario;

    @ManyToOne
    @JoinColumn(name = "parada_inicial_id")
    private Parada paradaInicial;

    @ManyToOne
    @JoinColumn(name = "parada_final_id")
    private Parada paradaFinal;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Boolean estadoPausado;
    private LocalDate pausaInicial;
    private LocalDate pausaFinal;
    private Integer pausaAcumulada;
}
