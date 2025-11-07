package org.example.microserviciomonopatin.controller;


import org.example.microserviciomonopatin.entity.Monopatin;
import org.example.microserviciomonopatin.repository.MonopatinRepository;
import org.example.microserviciomonopatin.service.MonopatinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monopatin")
public class MonopatinController {

    @Autowired
    private MonopatinService monopatinService;

    @GetMapping("/")
    public ResponseEntity<List<Monopatin>> getAll() {
        List<Monopatin> monopatines = monopatinService.getAll();
        if (monopatines.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return  ResponseEntity.ok(monopatines);
    }


    @PostMapping("")
    public ResponseEntity<Monopatin> save(@RequestBody Monopatin monopatin) {
        Monopatin monopatinNew = monopatinService.save(monopatin);
        return  ResponseEntity.ok(monopatinNew);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Monopatin> delete(@PathVariable("id") Long id) {
        Monopatin monopatin = monopatinService.findById(id);
        monopatinService.deleteById(id);
        return ResponseEntity.ok(monopatin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Monopatin> update(@PathVariable("id") Long id, @RequestBody Monopatin monopatin) {
        Monopatin monopatinActualizado = monopatinService.update(id, monopatin);
        if (monopatinActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return   ResponseEntity.ok(monopatinService.save(monopatinActualizado));
    }

    @GetMapping("/monopatinesCercanos/longitud/{longitud}/latitud/{latitud}")
    public ResponseEntity<List<Monopatin>> getMonopatinesCercanos(@PathVariable int longitud, @PathVariable int latitud, @RequestParam int longMaxRango, @RequestParam int latMaxRango) {
        List<Monopatin> monopatinesCercanos =   monopatinService.findCercanos(longitud, latitud, longMaxRango, latMaxRango);
        return  ResponseEntity.ok(monopatinesCercanos);
    }


}
