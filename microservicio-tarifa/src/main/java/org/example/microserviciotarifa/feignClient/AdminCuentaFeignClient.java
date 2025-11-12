package org.example.microserviciotarifa.feignClient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservicio-cuenta", url = "http://localhost:8005")
public interface AdminCuentaFeignClient {
    @GetMapping("/usuarios/{id}/rol")
    String getRol(@PathVariable Long id);

}
