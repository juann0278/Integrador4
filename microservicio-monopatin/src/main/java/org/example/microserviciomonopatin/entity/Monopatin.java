package org.example.microserviciomonopatin.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.*;


@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Monopatin{
    @Id
    private Long id;
    private int longitud;
    private int latitud;
    private float kmsAcumulados;
    private Long id_viaje;
    private Long id_parada;
    private String estado;
    private boolean disponible;
    private double tiempoDeUso;

}

