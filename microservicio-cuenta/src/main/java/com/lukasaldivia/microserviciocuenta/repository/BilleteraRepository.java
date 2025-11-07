package com.lukasaldivia.microserviciocuenta.repository;

import com.lukasaldivia.microserviciocuenta.entity.Billetera;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BilleteraRepository extends JpaRepository<Billetera,Long> {
}
