package org.example.microservicioviaje.controller;

import org.example.microservicioviaje.entity.Viaje;
import org.example.microservicioviaje.model.Monopatin;
import org.example.microservicioviaje.model.Usuario;
import org.example.microservicioviaje.service.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/viajes")
public class ViajeController {

    @Autowired
    ViajeService viajeService;

    @GetMapping("/")
    public ResponseEntity<List<Viaje>> getAllViajes(){
        List<Viaje> viajes = viajeService.getAll();
        if (viajes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(viajes);
    }

    @PostMapping("")
    public ResponseEntity<Viaje> save(@RequestBody Viaje viaje){
        Viaje newdViaje = viajeService.save(viaje);
        return ResponseEntity.ok(newdViaje);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Viaje> delete(@PathVariable Long id){
        viajeService.deleteId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Viaje> update(@PathVariable Long id, @RequestBody Viaje viaje){
        viaje.setId(id);
        Viaje updatedViaje = viajeService.update(viaje);
        return ResponseEntity.ok(updatedViaje);
    }

    //4)c) Como administrador quiero consultar los monopatines con mas de X viajes en un cierto año
    @GetMapping("/monopatines-mas-viajes")
    public ResponseEntity<List<Monopatin>> obtenerMonopatinesMasViajes(@RequestParam int cantidad, @RequestParam int anio){
        List<Monopatin> lista = viajeService.obtenerMonopatinesMasViajes(cantidad, anio);
        return ResponseEntity.ok(lista);
    }

    //e. Como administrador quiero ver los usuarios que más utilizan los monopatines, filtrado por
    //período y por tipo de usuario.
    @GetMapping("/usuarios-mayor-uso")
    public ResponseEntity<List<Usuario>> obtenerUsuariosMayorUso(@RequestParam LocalDate ini,@RequestParam LocalDate fin , @RequestParam String tipoUsuario){
        List<Usuario> usuarios = viajeService.findUsuariosMayorUso(ini, fin, tipoUsuario);
        return ResponseEntity.ok(usuarios);
    }

    //4)h) Como usuario quiero saber cuánto he usado los monopatines en un período, y opcionalmente si
    //otros usuarios relacionados a mi cuenta los han usado.
    @GetMapping("/uso")
    public ResponseEntity<Double>  obtenerUsoMonopatin(@RequestParam Long idUsuario, @RequestParam LocalDate fechaInicio, @RequestParam LocalDate fechaFin, @RequestParam(defaultValue = "false") boolean incluirRelacionados){
        double totalUso = viajeService.calcularUso(idUsuario, fechaInicio, fechaFin, incluirRelacionados);
        return ResponseEntity.ok(totalUso);
    }
}
