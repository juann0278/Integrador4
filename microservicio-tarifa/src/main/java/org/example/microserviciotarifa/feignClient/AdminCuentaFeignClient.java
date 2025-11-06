package org.example.microserviciotarifa.feignClient;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient (name = "microservicio-cuenta")  // luego falta agregar la url aca

public interface AdminCuentaFeignClient {
}
