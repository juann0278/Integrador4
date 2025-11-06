package org.example.microserviciomantenimiento.controller;


import org.example.microserviciomantenimiento.entity.Mantenimiento;
import org.example.microserviciomantenimiento.models.Monopatin;
import org.example.microserviciomantenimiento.service.MantenimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mantenimiento")
public class MantenimientoController {

    @Autowired
    private MantenimientoService service;

    @PostMapping("")
    public ResponseEntity<Mantenimiento> save(@RequestBody Mantenimiento mantenimiento) {
        Mantenimiento entity = service.save(mantenimiento);
        return ResponseEntity.ok(entity);
    }

    @GetMapping("")
    public ResponseEntity<List<Mantenimiento>> getAll() {
        List<Mantenimiento> mantenimientos = service.findAll();
        if(mantenimientos.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(mantenimientos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mantenimiento> getById(@PathVariable("id") Long id) {
        Mantenimiento mantenimiento = service.findById(id);
        if(mantenimiento == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mantenimiento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mantenimiento> deleteById(@PathVariable("id") Long id) {
        Mantenimiento mantenimiento = service.findById(id);
        service.deleteById(id);
        return ResponseEntity.ok(mantenimiento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mantenimiento>  update(@PathVariable("id") Long id, @RequestBody Mantenimiento mantenimiento) {
        Mantenimiento entity = service.update(id, mantenimiento);
        return ResponseEntity.ok(entity);
    }

    @PostMapping("/monopatin/{idMonopatin}")
    public ResponseEntity<Mantenimiento>  saveMonopatin(@PathVariable("idMonopatin") Long idMonopatin){
        service.saveMonopatin(idMonopatin);
        return null;
    }
}
