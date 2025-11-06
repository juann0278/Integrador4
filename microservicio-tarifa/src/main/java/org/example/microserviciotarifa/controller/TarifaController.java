package org.example.microserviciotarifa.controller;

import org.example.microserviciotarifa.entity.Tarifa;
import org.example.microserviciotarifa.service.TarifaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarifas")
public class TarifaController {

    @Autowired
    TarifaService tarifaService;

    @GetMapping()
    public ResponseEntity<List<Tarifa>> findAll() {
        List<Tarifa> tarifas = tarifaService.getAll();
        if (tarifas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tarifas);
    }

    @PostMapping("")
    public ResponseEntity<Tarifa> save(@RequestBody Tarifa tarifa) {
        Tarifa newTarifa = tarifaService.save(tarifa);
        return ResponseEntity.ok(newTarifa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tarifaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarifa> update (@PathVariable Long id, @RequestBody Tarifa tarifa ) {
        tarifa.setId(id);
        Tarifa updatedTarifa = tarifaService.update(tarifa);
        return ResponseEntity.ok(updatedTarifa);
    }


    @PutMapping("/ajustar/{idCuenta}")
    public ResponseEntity<Tarifa> ajustar(@PathVariable Long idCuenta, @RequestBody Tarifa tarifa) {
        Tarifa updated = tarifaService.ajustar(idCuenta, tarifa);
        return ResponseEntity.ok(updated);
    }


}
