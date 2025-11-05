package org.example.microservicioviaje.controller;

import org.example.microservicioviaje.entity.Parada;
import org.example.microservicioviaje.service.ParadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paradas")
public class ParadaController {

    @Autowired
    private ParadaService paradaService;

    @GetMapping("/")
    public ResponseEntity<List<Parada>> findAllParadas() {
        List<Parada> paradas = paradaService.findAll();
        if(paradas.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(paradas);
    }

    @PostMapping("")
    public ResponseEntity<Parada> save(@RequestBody Parada parada){
        Parada entity = this.paradaService.save(parada);
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<Parada> delete(@PathVariable Long id){
        Parada Deleted = this.paradaService.deleteById(id);
        return ResponseEntity.ok(Deleted);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Parada> update(@PathVariable Long id, @RequestBody Parada parada){
        Parada entity = this.paradaService.update(id, parada);
        return ResponseEntity.ok(entity);
    }
}
