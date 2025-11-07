package org.example.microserviciomonopatin.feignClients;

import org.example.microserviciomonopatin.entity.Monopatin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient (name = "microservicio-viaje", url = "http://localhost:8006/viajes")
public interface ViajeFeignClient {


    @GetMapping("/cantViajes/{cantViajes}/año/{anio}")
    List<Monopatin> getMonopatinesXViajesYAnio();

    @GetMapping("/monopatin/{id}/tiempoUso")
    double getTiempoUsoTotal(@PathVariable Long id);

    @GetMapping("/monopatin/{id}/pausaAcumulada")
    double getPausaAcumulada(@PathVariable Long id);

}
