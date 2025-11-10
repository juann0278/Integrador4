package org.example.microservicioviaje.service;

import org.example.microservicioviaje.entity.Viaje;
import org.example.microservicioviaje.feignClient.CuentaFeignClient;
import org.example.microservicioviaje.feignClient.MonopatinFeignClient;
import org.example.microservicioviaje.model.Monopatin;
import org.example.microservicioviaje.model.Usuario;
import org.example.microservicioviaje.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ViajeService {

    @Autowired
    private ViajeRepository viajeRepository;

    @Autowired
    private MonopatinFeignClient monopatinFeignClient;
    @Autowired
    private CuentaFeignClient cuentaFeignClient;

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

    public List<Monopatin> obtenerMonopatinesMasViajes(int anio, int cantidadMinima) {
        List<Viaje> viajesDelAnio = viajeRepository.findByYear(anio); // Traemos los viajes del año indicado
        // Contamos la cantidad de viajes por monopatín
        Map<Long, Long> conteo = viajesDelAnio.stream().collect(Collectors.groupingBy(Viaje::getId_monopatin, Collectors.counting()));
        // Filtramos los monopatines con más de X viajes
        List<Long> monopatinesId = conteo.entrySet().stream()
                .filter(entry -> entry.getValue() > cantidadMinima)
                .map(Map.Entry::getKey)
                .toList();
        // Llamamos al microservicio monopatín para traer los datos
        List<Monopatin> resultado = new ArrayList<>();
        for (Long id : monopatinesId) {
            Monopatin monopatin = monopatinFeignClient.getById(id);
            resultado.add(monopatin);
        }
        return resultado;
    }

    public List<Usuario> findUsuariosMayorUso(LocalDate inicio, LocalDate fin, String rol){
        List<Long> usuariosRol = cuentaFeignClient.getUsuarios(rol);

        if (usuariosRol.isEmpty()){
            return Collections.emptyList();
        }

        return viajeRepository.contarViajesPorUsuarioYRol(inicio, fin, usuariosRol);
    }

}

