package com.lukasaldivia.microserviciocuenta.service;

import com.lukasaldivia.microserviciocuenta.entity.Billetera;
import com.lukasaldivia.microserviciocuenta.entity.Rol;
import com.lukasaldivia.microserviciocuenta.entity.Usuario;
import com.lukasaldivia.microserviciocuenta.repository.BilleteraRepository;
import com.lukasaldivia.microserviciocuenta.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BilleteraRepository billeteraRepository;

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

    public Usuario update(Usuario from, Usuario to) {
        from.setNombre(to.getNombre());
        from.setApellido(to.getApellido());
        from.setEmail(to.getEmail());
        from.setCelular(to.getCelular());
        from.setPassword(to.getPassword());
        from.setRol(to.getRol());
        from.setMercadoPago(to.getMercadoPago());

        return usuarioRepository.save(from);
    }

    public Rol getRolById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if(usuario == null)
            return null;

        return usuario.getRol();
    }

    public Billetera agregarBilletera(Long usuarioId,Long billeteraId){
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

        if(usuario == null)
            return null;

        Billetera billetera = billeteraRepository.findById(billeteraId).orElse(null);

        if (billetera == null)
            return null;

        List<Billetera> billeteras = usuario.getBilleteras();

        billeteras.add(billetera);

        usuario.setBilleteras(billeteras);

        usuarioRepository.save(usuario);

        return billetera;


    }

    public List<Usuario> getUsuariosByBilleteraId(Long billeteraId){
        return usuarioRepository.findUsuariosByBilleteraId(billeteraId);
    }

    public List<Long> getUsuariosByRol(Rol rol){
        return usuarioRepository.findIdsByRol(rol);
    }

    public List<Long> obtenerUsuariosRelacionados(Long usuarioId) {
        return usuarioRepository.findUsuariosRelacionados(usuarioId);
    }


}
