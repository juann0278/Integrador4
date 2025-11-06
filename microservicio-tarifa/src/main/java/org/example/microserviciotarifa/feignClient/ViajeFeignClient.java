package org.example.microserviciotarifa.feignClient;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "microservicio-viaje", url = "http://localhost:8006")

public interface ViajeFeignClient {
}

