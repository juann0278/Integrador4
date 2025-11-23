package org.example.gateway.feignClients;


import jakarta.validation.Valid;
import org.example.gateway.entity.User;
import org.example.gateway.service.dto.user.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@FeignClient(name = "user", url = "http://localhost:8005/usuarios" )
public interface UserFeignClient {
    @GetMapping("/")
    Optional<User> findOneWithAuthoritiesByUsernameIgnoreCase(String lowerCase);
    @PostMapping("")
    void save(@Valid UserDTO userDTO);
}
