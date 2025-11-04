package org.example.microserviciotarifa.repository;

import org.example.microserviciotarifa.entity.Cobro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CobroRepository extends JpaRepository<Cobro, Long> {
}
