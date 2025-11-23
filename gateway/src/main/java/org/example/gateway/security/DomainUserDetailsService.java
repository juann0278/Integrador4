package org.example.gateway.security;

import org.example.gateway.feignClients.UserFeignClient;
import org.example.gateway.service.dto.user.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("userDetailsService")
public class DomainUserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);
    private final UserFeignClient userFeignClient;

    public DomainUserDetailsService( UserFeignClient userFeignClient ) {
        this.userFeignClient = userFeignClient;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username ) {
        log.debug("Authenticating {}", username);
        UserDTO
                .findOneWithAuthoritiesByUsernameIgnoreCase( username.toLowerCase() )
                .map( this::createSpringSecurityUser )
                .orElseThrow( () -> new UsernameNotFoundException( "El usuario " + username + " no existe" ) );
    }

    private UserDetails createSpringSecurityUser( User user ) {
        List<GrantedAuthority> grantedAuthorities = user
                .getAuthorities()
                .stream()
                .map( Authority::getName )
                .map( SimpleGrantedAuthority::new )
                .collect( Collectors.toList() );

        return new org.springframework.security.core.userdetails.User( user.getUsername(), user.getPassword(), grantedAuthorities );
    }
}
