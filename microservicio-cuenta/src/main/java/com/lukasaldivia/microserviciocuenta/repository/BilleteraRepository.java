package com.lukasaldivia.microserviciocuenta.repository;

import com.lukasaldivia.microserviciocuenta.entity.Billetera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BilleteraRepository extends JpaRepository<Billetera,Long> {

}
