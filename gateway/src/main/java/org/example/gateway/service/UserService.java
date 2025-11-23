package org.example.gateway.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.gateway.feignClients.UserFeignClient;
import org.example.gateway.repository.AuthorityRepository;
import org.example.gateway.service.dto.user.UserDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.example.gateway.entity.User;


@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserFeignClient userFeignClient;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    public long saveUser(@Valid UserDTO userDTO) {
        final User usuario = new User(userDTO.getUsername());
        usuario.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        final var roles = this.authorityRepository.findAllById(userDTO.getAuthorities());
        usuario.setAuthorities(roles);
        final var resultado = this.userFeignClient.save(usuario);
        return resultado.getId();
    }
}
