package com.lukasaldivia.microserviciocuenta.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Billetera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La suscripción no puede ser nula")
    private Boolean premium = false;

    private LocalDateTime fechaRegistro =  LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private EstadoCuenta estadoCuenta = EstadoCuenta.ACTIVA;

    @NotNull(message = "El saldo no puede ser nulo")
    @PositiveOrZero(message = "El saldo no puede ser negativo")
    private Float saldo = 0f;

    @ManyToMany(mappedBy = "billeteras")
    private List<Usuario> usuarios;
}
