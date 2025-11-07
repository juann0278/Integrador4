package com.lukasaldivia.microserviciocuenta.service;

import com.lukasaldivia.microserviciocuenta.entity.Billetera;
import com.lukasaldivia.microserviciocuenta.entity.EstadoCuenta;
import com.lukasaldivia.microserviciocuenta.entity.Rol;
import com.lukasaldivia.microserviciocuenta.entity.Usuario;
import com.lukasaldivia.microserviciocuenta.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).get();
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario update(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Rol getRolById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if(usuario == null)
            return null;

        return usuario.getRol();
    }

    public Float getSaldo(Long usuarioId, Long billeteraId){
        Billetera billetera = usuarioRepository.findBilleteraByUsuarioId(usuarioId, billeteraId);

        if(billetera == null)
            return null;

        return billetera.getSaldo();

    }

    public Boolean isPremium(Long usuarioId){
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if(usuario == null)
            return null;

        return usuario.isPremium();
    }

    public EstadoCuenta getEstadoCuenta(Long usuarioId){
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if(usuario == null)
            return null;

        return usuario.getEstadoCuenta();
    }

    public EstadoCuenta setEstadoCuenta(Boolean estadoCuenta, Long usuarioId){
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if(usuario == null)
            return null;

        EstadoCuenta estado = EstadoCuenta.ACTIVA;

        if (!estadoCuenta){
            estado = EstadoCuenta.ANULADA;
        }

        usuario.setEstadoCuenta(estado);
        update(usuario);
        return estado;
    }


}
