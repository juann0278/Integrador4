package org.example.microserviciomantenimiento.feignClient;


import org.example.microserviciomantenimiento.models.Monopatin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="microservicio-monopatin", url="http://localhost:8007/monopatines")
public interface MonopatinFeignClient {

    @GetMapping("/{id}")
    public Monopatin getById(@PathVariable("id") Long id);
}
