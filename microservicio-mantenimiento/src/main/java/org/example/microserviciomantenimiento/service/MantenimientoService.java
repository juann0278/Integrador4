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

    public  void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Mantenimiento update(Long id, Mantenimiento mantenimiento) {
        Mantenimiento updateMantenimiento = repository.findById(id).orElse(null);
        updateMantenimiento.setIdMonopatin(mantenimiento.getIdMonopatin());
        updateMantenimiento.setDescripcion(mantenimiento.getDescripcion());
        updateMantenimiento.setFechaFin(mantenimiento.getFechaFin());
        updateMantenimiento.setFechaInicio(mantenimiento.getFechaInicio());
        return repository.save(updateMantenimiento);
    }

}
