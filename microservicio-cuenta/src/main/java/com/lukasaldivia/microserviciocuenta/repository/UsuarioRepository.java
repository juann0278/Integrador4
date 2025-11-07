package com.lukasaldivia.microserviciocuenta.repository;

import com.lukasaldivia.microserviciocuenta.entity.Billetera;
import com.lukasaldivia.microserviciocuenta.entity.Rol;
import com.lukasaldivia.microserviciocuenta.entity.Usuario;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    @Query("SELECT b FROM Usuario u JOIN u.billeteras b WHERE u.id = :usuarioId AND b.id = :billeteraId")
    Billetera findBilleteraByUsuarioId(
            @Param("usuarioId") Long usuarioId,
            @Param("billeteraId") Long billeteraId
    );

}
