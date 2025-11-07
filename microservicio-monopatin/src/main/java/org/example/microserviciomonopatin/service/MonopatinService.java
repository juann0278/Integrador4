package org.example.microserviciomonopatin.service;


import org.example.microserviciomonopatin.dto.MonopatinReporteDTO;
import org.example.microserviciomonopatin.entity.Monopatin;
import org.example.microserviciomonopatin.repository.MonopatinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            monopatinNew.setTiempoDeUso(monopatin.getTiempoDeUso());
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


    public List<MonopatinReporteDTO> getMonopatinesConPausa() {
        List<Monopatin> monopatinesByPausa = monopatinRepository.getAllMonopatinesByPausa();

    }
}
