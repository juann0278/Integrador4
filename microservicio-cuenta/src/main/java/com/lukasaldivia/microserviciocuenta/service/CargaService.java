package com.lukasaldivia.microserviciocuenta.service;

import com.lukasaldivia.microserviciocuenta.entity.Billetera;
import com.lukasaldivia.microserviciocuenta.entity.Carga;
import com.lukasaldivia.microserviciocuenta.repository.CargaRepository;
import com.lukasaldivia.microserviciocuenta.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CargaService {

    @Autowired
    private CargaRepository cargaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BilleteraService billeteraService;

    public Carga agregarSaldo(Long billeteraId, Float monto) {

        // Primero actualizamos el saldo de la billetera
        Billetera billeteraActualizada = billeteraService.agregarSaldo(billeteraId, monto);

        if(billeteraActualizada == null){
            return null;
        }

        // Luego registramos la carga
        Carga carga = new Carga();
        carga.setBilletera(billeteraActualizada);
        carga.setMonto(monto);

        return cargaRepository.save(carga);
    }

}
