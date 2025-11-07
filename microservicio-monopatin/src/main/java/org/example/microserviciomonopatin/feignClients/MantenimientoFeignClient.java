package org.example.microserviciomonopatin.feignClients;



import org.example.microserviciomonopatin.models.Mantenimiento;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "microservicio-mantenimiento", url = "http://localhost:8003/mantenimiento")
public interface MantenimientoFeignClient {
    @PostMapping("")
    public Mantenimiento saveMantenimiento(@RequestBody Mantenimiento mantenimiento);
}
