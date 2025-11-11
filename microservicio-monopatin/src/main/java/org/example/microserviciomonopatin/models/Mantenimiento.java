package org.example.microserviciomonopatin.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mantenimiento {
    private Long id;
    private Long idMonopatin;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String descripcion;

}
