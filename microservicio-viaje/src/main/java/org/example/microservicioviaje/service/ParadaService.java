package org.example.microservicioviaje.service;

import org.example.microservicioviaje.entity.Parada;
import org.example.microservicioviaje.repository.ParadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParadaService {

    @Autowired
    private ParadaRepository paradaRepository;

    public List<Parada> findAll() {
        return this.paradaRepository.findAll();
    }

    public Parada save(Parada parada) {
        return this.paradaRepository.save(parada);
    }

    public Parada deleteById(Long id) {
        if(!this.paradaRepository.existsById(id)) {
            throw new RuntimeException("Parada no encontrada");
        }
        Parada deletedParada = this.paradaRepository.findById(id).orElse(null);
        this.paradaRepository.deleteById(id);
        return deletedParada;
    }

    public Parada update(Long id, Parada parada) {
        if(!this.paradaRepository.existsById(id)) {
            throw new RuntimeException("Parada no encontrada");
        }
        Parada updateParada = this.paradaRepository.findById(parada.getId()).orElse(null);
        updateParada.setNombre(parada.getNombre());
        updateParada.setDireccion(parada.getDireccion());
        updateParada.setLatitud(parada.getLatitud());
        updateParada.setLongitud(parada.getLongitud());
        return this.paradaRepository.save(updateParada);
    }
}
