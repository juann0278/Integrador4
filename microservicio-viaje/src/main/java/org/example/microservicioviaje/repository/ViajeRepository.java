package org.example.microservicioviaje.repository;

import org.example.microservicioviaje.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.example.microservicioviaje.entity.Viaje;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ViajeRepository extends JpaRepository<Viaje, Long> {

    @Query("SELECT v FROM Viaje v WHERE YEAR(v.fechaInicio) = :anio")
    List<Viaje> findByYear(int anio);

    @Query("SELECT new org.example.microservicioviaje.model.Usuario(v.idUsuario, COUNT(v)) " +
            "FROM Viaje v " +
            "WHERE v.fechaInicio BETWEEN :inicio AND :fin " +
            "AND v.idUsuario IN :usuarios " +
            "GROUP BY v.idUsuario " +
            "ORDER BY COUNT(v) DESC")
    List<Usuario> contarViajesPorUsuarioYRol(
            @Param("inicio") LocalDate inicio,
            @Param("fin") LocalDate fin,
            @Param("usuarios") List<Long> usuarios);

    List<Viaje> findByIdUsuarioInAndFechaInicioBetween(List<Long> usuarios, LocalDate inicio, LocalDate fin);
}
