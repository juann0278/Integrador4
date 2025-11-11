package com.lukasaldivia.microserviciocuenta.controller;

import com.lukasaldivia.microserviciocuenta.entity.Billetera;
import com.lukasaldivia.microserviciocuenta.entity.EstadoCuenta;
import com.lukasaldivia.microserviciocuenta.entity.Rol;
import com.lukasaldivia.microserviciocuenta.entity.Usuario;
import com.lukasaldivia.microserviciocuenta.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll(){
        List<Usuario> res =  usuarioService.findAll();

        if (res.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable("id") Long id){
        Usuario user = usuarioService.findById(id);

        if(user == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Usuario> save(@Valid @RequestBody Usuario usuario){
        Usuario savedUser = usuarioService.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable("id") Long id, @Valid @RequestBody Usuario usuario){
        Usuario user = usuarioService.findById(id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuarioService.update(user, usuario));
    }

    @PutMapping("/{id}/billetera")
    public ResponseEntity<Billetera> update(@PathVariable("id") Long id, @RequestBody Long id_billetera){
        Usuario user = usuarioService.findById(id);

        if (user == null)
            return ResponseEntity.notFound().build();

        Billetera res = usuarioService.agregarBilletera(id,id_billetera);

        if (res == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}/rol")
    public ResponseEntity<Rol> getRol(@PathVariable("id") Long id){
        Rol rol = usuarioService.getRolById(id);

        if(rol == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(rol);
    }

    @GetMapping("/{rol}")
    public ResponseEntity<List<Long>> getUsuarios(@Valid @PathVariable("rol") Rol rol){
        List<Long> ids = usuarioService.getUsuariosByRol(rol);
        if(ids == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ids);
    }

    @GetMapping("/relacionados/{usuarioId}")
    public List<Long> obtenerUsuariosRelacionados(@PathVariable("usuarioId") Long usuarioId) {
        return usuarioService.obtenerUsuariosRelacionados(usuarioId);
    }

}
