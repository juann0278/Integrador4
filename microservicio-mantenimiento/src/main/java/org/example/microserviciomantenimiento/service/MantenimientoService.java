package org.example.microserviciomantenimiento.service;

import org.example.microserviciomantenimiento.entity.Mantenimiento;
import org.example.microserviciomantenimiento.repository.MantenimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MantenimientoService {

    @Autowired
    private MantenimientoRepository repository;

    public Mantenimiento save(Mantenimiento mantenimiento) {
        return repository.save(mantenimiento);
    }

    public List<Mantenimiento> findAll() {
        return repository.findAll();
    }

    public Mantenimiento findById(Long id) {
        return repository.findById(id).orElse(null);
    }

}
