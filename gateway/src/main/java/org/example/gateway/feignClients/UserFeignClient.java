package org.example.gateway.feignClients;


import jakarta.validation.Valid;
import org.example.gateway.service.dto.user.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(name = "user", url = "http://localhost:8005/usuarios" )
public interface UserFeignClient {
    @GetMapping("/userName")
    Optional<?> getUserByUsername(@RequestParam(value = "username") String username);
    @PostMapping("")
    void save(@Valid UserDTO userDTO);
}
