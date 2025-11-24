package org.example.gateway.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.gateway.entity.User;
import org.example.gateway.feignClients.UserFeignClient;
import org.example.gateway.repository.AuthorityRepository;
import org.example.gateway.repository.UserRepository;
import org.example.gateway.service.dto.user.UserDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    public Long saveUser(@Valid UserDTO request) {
        final var user = new User(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        final var roles =  this.authorityRepository.findAllById(request.getAuthorities());
        user.setAuthorities(roles);
        final var result = this.userRepository.save(user);
        return result.getId();
    }
}
