package com.lukasaldivia.microserviciocuenta.service;

import com.lukasaldivia.microserviciocuenta.entity.Billetera;
import com.lukasaldivia.microserviciocuenta.entity.EstadoCuenta;
import com.lukasaldivia.microserviciocuenta.entity.Usuario;
import com.lukasaldivia.microserviciocuenta.repository.BilleteraRepository;
import com.lukasaldivia.microserviciocuenta.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BilleteraService {

    @Autowired
    private BilleteraRepository billeteraRepository;

    private UsuarioService usuarioService;

    public Billetera findById(Long id){
        return billeteraRepository.findById(id).orElse(null);
    }

    public Billetera update(Billetera billetera){
        return billeteraRepository.save(billetera);
    }

    public Billetera agregarSaldo(Long billeteraId, Float monto) {
        Billetera billetera = findById(billeteraId);

        if(billetera == null){
            return null;
        }

        billetera.setSaldo(billetera.getSaldo() + monto);
        return billeteraRepository.save(billetera);
    }

    public Float getSaldo(Long billeteraId){

        Billetera billetera = findById(billeteraId);
        if(billetera == null){
            return null;
        }

        return billetera.getSaldo();
    }

    public EstadoCuenta getEstadoCuenta(Long billetaraId){
        Billetera billetera = billeteraRepository.findById(billetaraId).orElse(null);
        if(billetera == null)
            return null;

        return billetera.getEstadoCuenta();
    }

    public EstadoCuenta setEstadoCuenta(Boolean estadoCuenta, Long billeteraId){
        Billetera billetera = billeteraRepository.findById(billeteraId).orElse(null);
        if(billetera == null)
            return null;

        EstadoCuenta estado = EstadoCuenta.ACTIVA;

        if (estadoCuenta == null || !estadoCuenta){
            estado = EstadoCuenta.ANULADA;
        }

        billetera.setEstadoCuenta(estado);
        update(billetera);
        return estado;
    }

    public Boolean isPremium(Long billeteraId){
        Billetera billetera = findById(billeteraId);
        if(billetera == null){
            return null;
        }

        return billetera.getPremium();
    }

    public Billetera setPremium(Boolean premium, Long billeteraId){
        Billetera billetera = findById(billeteraId);
        if(billetera == null){
            return null;
        }

        billetera.setPremium(premium);

        return  billeteraRepository.save(billetera);
    }


    public List<Usuario> getUsuarios(Long billeteraId){
        List<Usuario> usuarios = usuarioService.getUsuariosByBilleteraId(billeteraId);

        return usuarios;
    }
}
