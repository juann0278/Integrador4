package org.example.microservicioviaje.feignClient;

import org.example.microservicioviaje.model.Monopatin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "microservicio-monopatin", url = "http://localhost:8007/monopatin")
public interface MonopatinFeignClient {

    @GetMapping("/{id}")
    Monopatin getById(@PathVariable("id") Long id);

    @PostMapping("/distancia-total")
    double obtenerDistanciaTotal(@RequestBody List<Long> monopatinIds);
}
