package org.example.microserviciomonopatin.service;


import org.example.microserviciomonopatin.entity.Monopatin;
import org.example.microserviciomonopatin.repository.MonopatinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonopatinService {
    @Autowired
    private MonopatinRepository monopatinRepository;

    public Monopatin save(Monopatin monopatin) {
        return monopatinRepository.save(monopatin);
    }

    public List<Monopatin> getAll() {
        return  monopatinRepository.findAll();
    }

    public Monopatin findById(Long id) {
        return monopatinRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        monopatinRepository.deleteById(id);
    }

    public Monopatin update(Monopatin monopatin) {
        return  monopatinRepository.save(monopatin);
    }
}
