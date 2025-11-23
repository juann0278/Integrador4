package org.example.gateway.repository;

import io.micrometer.observation.ObservationFilter;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient (name = "usuario", url = "http://localhost:")
public interface UserRepository {
    Optional<Usuario> findOneWithAuthoritiesByUsernameIgnoreCase(String lowerCase) {
    }
}
