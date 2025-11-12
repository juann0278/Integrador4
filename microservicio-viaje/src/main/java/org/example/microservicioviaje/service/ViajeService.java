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

    // Tiempo total de uso de un monopatín (en minutos o como quieras)
    public double getTiempoUsoTotal(Long idMonopatin) {
        List<Viaje> viajes = viajeRepository.findByIdMonopatin(idMonopatin);
        double tiempoTotal = 0;
        for (Viaje v : viajes) {
            if (v.getFechaInicio() != null && v.getFechaFin() != null) {
                // Calcula la diferencia en días; si quieres horas/minutos ajusta
                tiempoTotal += java.time.Duration.between(
                        v.getFechaInicio().atStartOfDay(),
                        v.getFechaFin().atStartOfDay()
                ).toMinutes();
            }
        }
        return tiempoTotal;
    }

    // Pausa acumulada de un monopatín (en minutos)
    public double getPausaAcumulada(Long idMonopatin) {
        List<Viaje> viajes = viajeRepository.findByIdMonopatin(idMonopatin);
        double pausaTotal = 0;
        for (Viaje v : viajes) {
            if (v.getPausaAcumulada() != null) {
                pausaTotal += v.getPausaAcumulada();
            }
        }
        return pausaTotal;
    }

    public List<Monopatin> obtenerMonopatinesMasViajes(int anio, int cantidadMinima) {
        List<Viaje> viajesDelAnio = viajeRepository.findByYear(anio); // Traemos los viajes del año indicado
        // Contamos la cantidad de viajes por monopatín
        Map<Long, Long> conteo = viajesDelAnio.stream().collect(Collectors.groupingBy(Viaje::getIdMonopatin, Collectors.counting()));
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

        System.out.println("usuariosRol: "+usuariosRol);
        if (usuariosRol.isEmpty()){
            return Collections.emptyList();
        }

        return viajeRepository.contarViajesPorUsuarioYRol(inicio, fin, usuariosRol);
    }

    public double calcularUso(Long usuarioId, LocalDate fechaInicio, LocalDate fechaFin, boolean incluirRelacionados) {
        //Obtengo todos los usuarios a incluir
        List<Long> usuarios = new ArrayList<>();
        usuarios.add(usuarioId);

        if (incluirRelacionados) {
            List<Long> relacionados = cuentaFeignClient.obtenerUsuariosRelacionados(usuarioId);
            usuarios.addAll(relacionados);
        }

        // Buscamos todos los viajes de esos usuarios en el período
        List<Viaje> viajes = viajeRepository.findByIdUsuarioInAndFechaInicioBetween(usuarios, fechaInicio, fechaFin);

        //Obtenemos los IDs de monopatines usados
        List<Long> monopatinIds = viajes.stream()
                .map(Viaje::getIdMonopatin)
                .distinct()
                .toList();

        //Consultamos al microservicio-monopatin para saber la distancia de cada uno
        double totalKm = monopatinFeignClient.obtenerDistanciaTotal(monopatinIds);

        return totalKm;
    }

}

