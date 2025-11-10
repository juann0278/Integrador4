package org.example.microservicioviaje.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "microservicio-cuenta", url = "http://localhost:8006/usuarios")
public interface CuentaFeignClient {

    @GetMapping("/{rol}")
    public List<Long> getUsuarios(@PathVariable("rol") String rol);

}
