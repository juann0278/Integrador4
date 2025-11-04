package org.example.microserviciomonopatin.service;


import org.example.microserviciomonopatin.repository.MonopatinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonopatinService {
    @Autowired
    private MonopatinRepository monopatinRepository;
}
