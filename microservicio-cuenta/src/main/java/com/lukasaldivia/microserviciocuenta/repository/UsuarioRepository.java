package com.lukasaldivia.microserviciocuenta.repository;

import com.lukasaldivia.microserviciocuenta.entity.Rol;
import com.lukasaldivia.microserviciocuenta.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // ✅ Devuelve todos los usuarios asociados a una billetera específica
    @Query("SELECT DISTINCT u FROM Usuario u JOIN u.billeteras b WHERE b.id = :billeteraId")
    List<Usuario> findUsuariosByBilleteraId(@Param("billeteraId") Long billeteraId);

    // ✅ Devuelve los IDs de los usuarios con un rol específico
    @Query("SELECT u.id FROM Usuario u WHERE u.rol = :rol")
    List<Long> findIdsByRol(@Param("rol") Rol rol);

    // ✅ Devuelve los IDs de usuarios relacionados por compartir una billetera
    @Query("SELECT DISTINCT u2.id " +
            "FROM Usuario u1 " +
            "JOIN u1.billeteras b " +
            "JOIN b.usuarios u2 " +
            "WHERE u1.id = :usuarioId " +
            "AND u2.id <> :usuarioId")
    List<Long> findUsuariosRelacionados(@Param("usuarioId") Long usuarioId);

}
