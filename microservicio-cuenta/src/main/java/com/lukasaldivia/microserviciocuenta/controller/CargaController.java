package com.lukasaldivia.microserviciocuenta.controller;

import com.lukasaldivia.microserviciocuenta.entity.Carga;
import com.lukasaldivia.microserviciocuenta.service.CargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cargas")
public class CargaController {

    @Autowired
    private CargaService cargaService;

    @PostMapping("/billetera/{billeteraId}")
    public ResponseEntity<Carga> cargar(
            @PathVariable Long billeteraId,
            @RequestParam Float monto
    ){
        Carga carga = cargaService.agregarSaldo(billeteraId, monto);

        if(carga == null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(carga);
    }


}
