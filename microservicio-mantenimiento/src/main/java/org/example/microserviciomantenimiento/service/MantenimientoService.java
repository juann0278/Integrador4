package org.example.microserviciomantenimiento.service;

import org.example.microserviciomantenimiento.entity.Mantenimiento;
import org.example.microserviciomantenimiento.feignClient.MonopatinFeignClient;
import org.example.microserviciomantenimiento.models.Monopatin;
import org.example.microserviciomantenimiento.repository.MantenimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class MantenimientoService {

    @Autowired
    private MantenimientoRepository repository;

    @Autowired
    private MonopatinFeignClient monopatinFeignClient; //revisar


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

    public Mantenimiento saveMonopatin(Long idMonopatin){
        Mantenimiento mantenimiento;
        Monopatin monopatin = monopatinFeignClient.getById(idMonopatin);
        if(monopatin == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Monopatin no encontrado");
        }
        if (!monopatin.isDisponible()){
            mantenimiento = new Mantenimiento();
            mantenimiento.setIdMonopatin(idMonopatin);
            mantenimiento.setFechaInicio(LocalDate.now());
            mantenimiento.setFechaFin(null);//revisar
            mantenimiento.setDescripcion("En arreglo indefinido");
            repository.save(mantenimiento);
        }
        else {
            mantenimiento = repository.findByIdMonopatin(idMonopatin);
        }

        return mantenimiento;
    }

}
