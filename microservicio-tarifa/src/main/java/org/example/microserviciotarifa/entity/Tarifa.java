package org.example.microserviciotarifa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Tarifa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float monto;
    private float monto_especial;
    private Date fecha_ini;
    private Date fecha_fin;

    @OneToMany(mappedBy = "tarifa")
    private List<Cobro> cobros;

}
