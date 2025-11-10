package com.lukasaldivia.microserviciocuenta.repository;

import com.lukasaldivia.microserviciocuenta.entity.Rol;
import com.lukasaldivia.microserviciocuenta.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    @Query("SELECT FROM Usuario u JOIN u.billeteras b WHERE b.id = :billeteraId")
    List<Usuario> findUsuariosByBilleteraId(@Param("billeteraId") Long id);

    @Query("SELECT id FROM Usuario u WHERE u.rol = :rol")
    List<Long> findIdsByRol(@Param("rol") Rol rol);

}
