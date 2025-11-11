package com.lukasaldivia.microserviciocuenta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class MicroservicioCuentaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicioCuentaApplication.class, args);
    }

}
