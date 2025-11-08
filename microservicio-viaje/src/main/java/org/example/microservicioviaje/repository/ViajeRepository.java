package org.example.microservicioviaje.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.microservicioviaje.entity.Viaje;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ViajeRepository extends JpaRepository<Viaje, Long> {

    @Query("SELECT v FROM Viaje v WHERE YEAR(v.fecha_inicio) = :anio")
    List<Viaje> findByYear(int anio);
}
