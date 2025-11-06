package org.example.microserviciotarifa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MicroservicioTarifaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicioTarifaApplication.class, args);
    }
}
