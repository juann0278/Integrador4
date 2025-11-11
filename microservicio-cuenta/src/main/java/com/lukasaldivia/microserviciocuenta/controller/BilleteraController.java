package com.lukasaldivia.microserviciocuenta.controller;

import com.lukasaldivia.microserviciocuenta.entity.Billetera;
import com.lukasaldivia.microserviciocuenta.entity.EstadoCuenta;
import com.lukasaldivia.microserviciocuenta.entity.Usuario;
import com.lukasaldivia.microserviciocuenta.service.BilleteraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/billeteras")
public class BilleteraController {

    @Autowired
    private BilleteraService billeteraService;

    @GetMapping
    public  ResponseEntity<List<Billetera>> findAll(){
        List<Billetera> res = billeteraService.findAll();

        if(res.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/{billeteraId}/saldo")
    public ResponseEntity<Float> getSaldo(@PathVariable Long billeteraId){
        Float saldo = billeteraService.getSaldo(billeteraId);

        if (saldo == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(saldo);
    }

    @GetMapping("/{billeteraId}/estado-cuenta")
    public ResponseEntity<Boolean> getEstadoCuenta(@PathVariable Long billeteraId){
        EstadoCuenta estadoCuenta = billeteraService.getEstadoCuenta(billeteraId);

        if(estadoCuenta == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(estadoCuenta == EstadoCuenta.ACTIVA);
    }

    @GetMapping("/{billeteraId}/premium")
    public ResponseEntity<Boolean> getPremium(@PathVariable Long billeteraId){
        Boolean premium = billeteraService.isPremium(billeteraId);

        if (premium == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(premium);
    }

    @PostMapping
    public ResponseEntity<Billetera> save(@Valid @RequestBody Billetera billetera){
        Billetera res = billeteraService.save(billetera);

        if (res == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(res);
    }

    @PutMapping("/{billeteraId}/estado-cuenta")
    public ResponseEntity<EstadoCuenta> save(
            @Valid @RequestBody Boolean estadoCuenta,
            @PathVariable Long billeteraId
    ){

        EstadoCuenta estado = billeteraService.setEstadoCuenta(estadoCuenta, billeteraId);

        if(estadoCuenta == null){
            return ResponseEntity.badRequest().build();
        }

        if (estado == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(estado);

    }

    @PutMapping("/{billeteraId}/premium")
    public ResponseEntity<Billetera> setPremium(
            @Valid @RequestBody Boolean premium,
            @PathVariable Long billeteraId
    ){
        Billetera billetera = billeteraService.setPremium(premium, billeteraId);

        if(billetera == null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(billetera);
    }

    @GetMapping("/{billeteraId}/usuarios")
    public ResponseEntity<List<Usuario>> getUsuarios(@PathVariable("billeteraId") Long billeteraId){

        List<Usuario> usuarios = billeteraService.getUsuarios(billeteraId);

        if (usuarios == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);

    }


}
