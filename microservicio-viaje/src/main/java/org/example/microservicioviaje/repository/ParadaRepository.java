package org.example.microservicioviaje.repository;

import org.example.microservicioviaje.entity.Parada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ParadaRepository extends JpaRepository<Parada, Long> {
}
