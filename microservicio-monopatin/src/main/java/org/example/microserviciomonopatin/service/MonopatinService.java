package org.example.microserviciomonopatin.service;


import org.example.microserviciomonopatin.dto.MonopatinReporteDTO;
import org.example.microserviciomonopatin.dto.MonopatinReportePausaDTO;
import org.example.microserviciomonopatin.entity.Monopatin;
import org.example.microserviciomonopatin.feignClients.MantenimientoFeignClient;
import org.example.microserviciomonopatin.feignClients.ViajeFeignClient;
import org.example.microserviciomonopatin.models.Mantenimiento;
import org.example.microserviciomonopatin.repository.MonopatinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MonopatinService {
    @Autowired
    private MonopatinRepository monopatinRepository;
    @Autowired
    private ViajeFeignClient viajeFeignClient;
    @Autowired
    private MantenimientoFeignClient mantenimientoFeignClient;

    public Monopatin save(Monopatin monopatin) {
        return monopatinRepository.save(monopatin);
    }

    public List<Monopatin> getAll() {
        return  monopatinRepository.findAll();
    }

    public Optional<Monopatin> findById(Long id) {
        return monopatinRepository.findById(id);
    }

    public void deleteById(Long id) {
        monopatinRepository.deleteById(id);
    }

    public Monopatin update(Long id, Monopatin monopatin) {
        Monopatin monopatinNew = monopatinRepository.findById(id).orElse(null);
        if  (monopatinNew != null) {
            monopatinNew.setEstado(monopatin.getEstado());
            monopatinNew.setDisponible(monopatin.isDisponible());
            monopatinNew.setLatitud(monopatin.getLatitud());
            monopatinNew.setLongitud(monopatin.getLongitud());
            monopatinNew.setKmsAcumulados(monopatin.getKmsAcumulados());
            monopatinNew.setId_parada(monopatin.getId_parada());
            monopatinNew.setId_viaje(monopatin.getId_viaje());
           // monopatinNew.setTiempoDeUso(monopatin.getTiempoDeUso());
        }else{
            return null;
        }
        return  monopatinRepository.save(monopatin);
    }

    public List<Monopatin> findCercanos(int longitud, int latitud, int longRangoMax, int latRangoMax) {
        int longMin = longitud - longRangoMax;
        int longMax = longitud + longRangoMax;
        int latMin = latitud - latRangoMax;
        int latMax = latitud + latRangoMax;
        return monopatinRepository.findCercanos(longMin, longMax, latMin, latMax);
    }

    public List<MonopatinReporteDTO> getMonopatinesByKms() {
        List<Monopatin> monopatinesByKms = monopatinRepository.getAllMonopatinesByKms();
        List<MonopatinReporteDTO> reporte = new ArrayList<>();
        for (Monopatin m : monopatinesByKms ){
            MonopatinReporteDTO dto = new MonopatinReporteDTO(
                    m.getId(),
                    m.getKmsAcumulados()
            );
            reporte.add(dto);
        }
        return reporte;
    }


    public List<MonopatinReportePausaDTO> getMonopatinesConYSinPausa() {
        List<Monopatin> monopatinesByPausa = viajeFeignClient.getAll();
        List<MonopatinReportePausaDTO> reporte = new ArrayList<>();
        for (Monopatin m : monopatinesByPausa) {
            MonopatinReportePausaDTO dto = new MonopatinReportePausaDTO(
                    m.getId(),
                    viajeFeignClient.getPausaAcumulada(m.getId()),
                    viajeFeignClient.getTiempoUsoTotal(m.getId())
            );
            reporte.add(dto);
        }
        return reporte;
    }


    public Mantenimiento registerMantenimiento(Long id, Monopatin monopatinMantenimiento) {
        Mantenimiento mantenimiento = new Mantenimiento();
        Monopatin monopatin = monopatinRepository.findById(id).orElse(null);
        if(monopatin.getEstado().equals("mantenimiento")){
            throw new RuntimeException("El monopatin ya se encuentra en mantenimiento");
        }
        monopatin.setEstado("mantenimiento");
        monopatinRepository.save(monopatin);

        mantenimiento.setDescripcion("en mantenimiento");
        mantenimiento.setIdMonopatin(monopatin.getId());
        mantenimiento.setFechaInicio(LocalDate.now());
        mantenimiento.setFechaFin(LocalDate.MAX);



        return mantenimientoFeignClient.saveMantenimiento(mantenimiento);
    }

}
