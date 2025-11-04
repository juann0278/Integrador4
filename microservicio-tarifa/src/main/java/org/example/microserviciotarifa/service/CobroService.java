package org.example.microserviciotarifa.service;

import org.example.microserviciotarifa.entity.Cobro;
import org.example.microserviciotarifa.repository.CobroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CobroService {

    @Autowired
    CobroRepository cobroRepository;

    public List<Cobro> getAll(){ return cobroRepository.findAll();}

    public Cobro save(Cobro cobro){
        Cobro newCobro;
        newCobro = cobroRepository.save(cobro);
        return newCobro;
    }

}
