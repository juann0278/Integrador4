package org.example.microservicioviaje.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.microservicioviaje.entity.Viaje;

public interface ViajeRepository extends JpaRepository<Viaje, Long> {
}
