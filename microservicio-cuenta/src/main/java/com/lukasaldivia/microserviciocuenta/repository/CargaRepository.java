package com.lukasaldivia.microserviciocuenta.repository;

import com.lukasaldivia.microserviciocuenta.entity.Carga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargaRepository extends JpaRepository<Carga,Long> {
}
