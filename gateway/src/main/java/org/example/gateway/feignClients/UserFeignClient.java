package org.example.gateway.feignClients;


import org.example.gateway.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "user", url = "http://localhost:8005/usuarios" )
public interface UserFeignClient extends JpaRepository<User, Long> {

    @Query("""
            FROM User u JOIN FETCH u.authorities
            WHERE lower(u.username) = ?1
            """)
    Optional<User> findOneWithAuthoritiesByUsernameIgnoreCase(@PathVariable String username);
}
