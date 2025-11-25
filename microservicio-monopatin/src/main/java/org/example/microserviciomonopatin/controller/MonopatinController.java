package org.example.microserviciomonopatin.controller;


import org.example.microserviciomonopatin.common.ApiResponse;
import org.example.microserviciomonopatin.dto.MonopatinReporteDTO;
import org.example.microserviciomonopatin.dto.MonopatinReportePausaDTO;
import org.example.microserviciomonopatin.entity.Monopatin;
import org.example.microserviciomonopatin.models.Mantenimiento;
import org.example.microserviciomonopatin.service.MonopatinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<Monopatin> getById(@PathVariable Long id) {
        Optional<Monopatin> monopatin = monopatinService.findById(id);
        return monopatin.map(ResponseEntity::ok).orElseGet
                (() -> ResponseEntity.notFound().build());
    }


    @PostMapping("")
    public ResponseEntity<Monopatin> save(@RequestBody Monopatin monopatin) {
        Monopatin monopatinNew = monopatinService.save(monopatin);
        return  ResponseEntity.ok(monopatinNew);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Monopatin>> delete(@PathVariable("id") Long id) {
        Optional<Monopatin> monopatin = monopatinService.findById(id);
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
    public ResponseEntity<ApiResponse<List<Monopatin>>> getMonopatinesCercanos(@PathVariable int longitud, @PathVariable int latitud, @RequestParam int longMaxRango, @RequestParam int latMaxRango) {
        List<Monopatin> monopatinesCercanos =   monopatinService.findCercanos(longitud, latitud, longMaxRango, latMaxRango);
        if (monopatinesCercanos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        //mock de microservicio google maps
        ApiResponse<List<Monopatin>> response = new ApiResponse<>("Ubicación obtenida por google maps", monopatinesCercanos);
        return  ResponseEntity.ok(response);
    }

    @GetMapping("/kms")
    public ResponseEntity<List<MonopatinReporteDTO>> getReporteMonopatinesByKms() {
        List<MonopatinReporteDTO> reporte = monopatinService.getMonopatinesByKms();
        return ResponseEntity.ok(reporte);
    }


    @PutMapping("/mantenimiento/{id}")
    public ResponseEntity<Mantenimiento> registerMantenimiento(@PathVariable Long id) {
        Mantenimiento mantenimiento = monopatinService.registerMantenimiento(id);
        return  ResponseEntity.ok(mantenimiento);
    }



    @GetMapping("/usoConPausa")
    public ResponseEntity<?> getReporteMonopatinesConYSinPausa(){
     List<MonopatinReportePausaDTO> reporte = monopatinService.getMonopatinesConYSinPausa();
     return ResponseEntity.ok(reporte);
    }

    @PostMapping("/distancia-total")
    public ResponseEntity<Double> obtenerDistanciaTotal(@RequestBody List<Long> monopatinIds) {
        double total = monopatinService.obtenerDistanciaTotal(monopatinIds);
        return ResponseEntity.ok(total);
    }
}
