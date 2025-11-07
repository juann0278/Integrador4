package com.lukasaldivia.microserviciocuenta.service;

import com.lukasaldivia.microserviciocuenta.entity.Billetera;
import com.lukasaldivia.microserviciocuenta.repository.BilleteraRepository;
import com.lukasaldivia.microserviciocuenta.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BilleteraService {

    @Autowired
    private BilleteraRepository billeteraRepository;

    public Billetera findById(Long id){
        return billeteraRepository.findById(id).orElse(null);
    }

    public Billetera agregarSaldo(Long billeteraId, Float monto) {
        Billetera billetera = findById(billeteraId);

        if(billetera == null){
            return null;
        }

        billetera.setSaldo(billetera.getSaldo() + monto);
        return billeteraRepository.save(billetera);
    }

}
