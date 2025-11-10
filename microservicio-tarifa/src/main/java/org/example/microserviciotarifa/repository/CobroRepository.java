package org.example.microserviciotarifa.repository;

import feign.Param;
import org.example.microserviciotarifa.entity.Cobro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CobroRepository extends JpaRepository<Cobro, Long> {

    @Query("SELECT SUM (c.monto) from Cobro c WHERE c.fecha BETWEEN :inicio AND :fin")
     Double totalRecaudadoEFechas(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);

}
