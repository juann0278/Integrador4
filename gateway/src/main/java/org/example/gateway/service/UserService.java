package org.example.gateway.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.gateway.feignClients.UserFeignClient;
import org.example.gateway.service.dto.user.UserDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserFeignClient userFeignClient;
    private final PasswordEncoder passwordEncoder;

    public void saveUser(@Valid UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userFeignClient.save(userDTO);
    }
}
