package org.example.microserviciomantenimiento.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Monopatin{

    private Long id;
    private String longitud;
    private String latitud;
    private float kmsAcumulados;
    private Long id_viaje;
    private Long id_parada;
    private String estado;
    private boolean disponible;
    private double tiempoDeUso;

}