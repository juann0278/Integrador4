package org.example.microserviciotarifa.service;

import org.example.microserviciotarifa.entity.Tarifa;
import org.example.microserviciotarifa.repository.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarifaService {

    @Autowired
    TarifaRepository tarifaRepository;

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

}
