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
    private Long id_monopatin;
    private Long id_usuario;

    @ManyToOne
    @JoinColumn(name = "parada_inicial_id")
    private Parada parada_inicial;

    @ManyToOne
    @JoinColumn(name = "parada_final_id")
    private Parada parada_final;

    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private Boolean estadoPausado;
    private LocalDate pausa_inicial;
    private LocalDate pausa_final;
    private Integer pausa_acumulada;
}
