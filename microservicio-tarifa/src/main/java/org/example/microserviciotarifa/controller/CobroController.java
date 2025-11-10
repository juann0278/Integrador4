package org.example.microserviciotarifa.controller;


import org.example.microserviciotarifa.entity.Cobro;
import org.example.microserviciotarifa.entity.Tarifa;
import org.example.microserviciotarifa.service.CobroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cobros")
public class CobroController {

    @Autowired
    CobroService cobroService;


    @GetMapping("/")
    public ResponseEntity<List<Cobro>> findAll() {
        List<Cobro> cobros = cobroService.getAll();
        if(cobros.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cobros);
    }

    @PostMapping("")
     public ResponseEntity<Cobro> save(@RequestBody Cobro cobro) {
        Cobro newCobro = cobroService.save(cobro);
        return ResponseEntity.ok(newCobro);
    }


    @GetMapping("/total/{anio}/{mesIni}/{mesFin}")
    public ResponseEntity<Double> getTotal (@PathVariable int anio, @PathVariable int mesIni, @PathVariable int mesFin){
        double total = cobroService.getTotalRecaudado(anio,mesIni,mesFin);
        return ResponseEntity.ok(total);
    }

}
