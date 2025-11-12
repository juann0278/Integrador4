package org.example.microserviciotarifa.service;

import com.lukasaldivia.microserviciocuenta.entity.Rol;
import org.example.microserviciotarifa.entity.Tarifa;
import org.example.microserviciotarifa.feignClient.AdminCuentaFeignClient;
import org.example.microserviciotarifa.repository.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public class TarifaService {

    @Autowired
    TarifaRepository tarifaRepository;
    @Autowired
    AdminCuentaFeignClient adminCuentaFeignClient;

    public List<Tarifa> getAll(){
        return tarifaRepository.findAll();
    }

    public Tarifa save(Tarifa tarifa){
        Tarifa newTarifa;
        newTarifa = tarifaRepository.save(tarifa);
        return newTarifa;
    }

    public void deleteById(Long id){
        if(!tarifaRepository.existsById(id)){
            throw new RuntimeException("Tarifa no encontrada");
        }
        tarifaRepository.deleteById(id);
    }

    public Tarifa update(Tarifa tarifa){

        if(!tarifaRepository.existsById(tarifa.getId())){
            throw new RuntimeException("Tarifa no encontrada");
        }

        return tarifaRepository.save(tarifa);
    }

    public Tarifa ajustar(Long idCuenta, Tarifa nuevaTarifa){

        Rol rol = adminCuentaFeignClient.getRol(idCuenta);

        if(rol == Rol.ADMINISTRADOR){
            return tarifaRepository.save(nuevaTarifa);
        }

       throw new ResponseStatusException(HttpStatus.FORBIDDEN , ("No tiene permisos para ajustar el precio de tarifas"));

    }



}
