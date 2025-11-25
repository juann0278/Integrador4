package com.lukasaldivia.microserviciocuenta.controller;

import com.lukasaldivia.microserviciocuenta.entity.Carga;
import com.lukasaldivia.microserviciocuenta.model.ApiResponse;
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
    public ResponseEntity<ApiResponse<Carga>> cargar(
            @PathVariable Long billeteraId,
            @RequestParam Float monto
    ){
        Carga carga = cargaService.agregarSaldo(billeteraId, monto);

        if(carga == null){
            return ResponseEntity.badRequest().build();
        }
        //mock de mercado pago
        ApiResponse<Carga> response = new ApiResponse<>("Se debitaron " +monto+ "pesos de tu cuenta de mercado pago", carga);
        return ResponseEntity.ok(response);
    }


}
