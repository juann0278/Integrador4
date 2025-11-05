package org.example.microservicioviaje.service;

import org.example.microservicioviaje.entity.Viaje;
import org.example.microservicioviaje.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViajeService {

    @Autowired
    private ViajeRepository viajeRepository;

    public List<Viaje> getAll(){ return viajeRepository.findAll();}

    public Viaje save(Viaje viaje){
        Viaje viajeNew;
        viajeNew = viajeRepository.save(viaje);
        return viajeNew;
    }

    public void deleteId(Long id){
        if(!viajeRepository.existsById(id)){
            throw new RuntimeException("Viaje no encontrado");
        }
        viajeRepository.deleteById(id);
    }

    public Viaje update(Viaje viaje){
        if(!viajeRepository.existsById(viaje.getId())){
            throw new RuntimeException("Viaje no encontrado");
        }
        return viajeRepository.save(viaje);
    }

}

