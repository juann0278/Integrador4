package org.example.microserviciomantenimiento.feignClient;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="microservicio-monopatin", url="http://localhost:800x/monopatines")
public interface MonopatinFeignClient {
}
