package org.example.microserviciomantenimiento.controller;


import org.example.microserviciomantenimiento.entity.Mantenimiento;
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
    public ResponseEntity<Mantenimiento> finById(@PathVariable("id") Long id) {
        Mantenimiento mantenimiento = service.findById(id);
        if(mantenimiento == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mantenimiento);
    }
}
