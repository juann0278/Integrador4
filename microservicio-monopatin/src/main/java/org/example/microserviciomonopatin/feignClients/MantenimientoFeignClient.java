package org.example.microserviciomonopatin.feignClients;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "microservicio-mantenimiento", url = "http://localhost:8003/mantenimiento")
public interface MantenimientoFeignClient {

}
