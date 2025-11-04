package org.example.microserviciomantenimiento.controller;


import org.example.microserviciomantenimiento.entity.Mantenimiento;
import org.example.microserviciomantenimiento.service.MantenimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
