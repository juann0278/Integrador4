package org.example.gateway.config;

import org.example.gateway.security.AuthorityConstant;
import org.example.gateway.security.jwt.JwtFilter;
import org.example.gateway.security.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final TokenProvider tokenProvider;

    public SecurityConfig( TokenProvider tokenProvider ) {
        this.tokenProvider = tokenProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain( final HttpSecurity http ) throws Exception {
        http.csrf( AbstractHttpConfigurer::disable );
        http.sessionManagement( s -> s.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) );
        http
                .securityMatcher("/api/**" )
                .authorizeHttpRequests( authz -> authz
                        .requestMatchers(HttpMethod.POST, "/api/token").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/authenticate").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/registro").permitAll()
                        .requestMatchers(HttpMethod.PUT, "api/billeteras/{billeteraId}/estado-cuenta").hasAuthority(AuthorityConstant._ADMIN)
                        .requestMatchers(HttpMethod.POST, "/api/cargas/billetera/{billeteraId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/monopatin/monopatinesCercanos/longitud/{longitud}/latitud/{latitud}").hasAnyAuthority(AuthorityConstant._USUARIO, AuthorityConstant._PREMIUM)
                        .requestMatchers(HttpMethod.GET, "/api/monopatin/kms").hasAuthority(AuthorityConstant._ADMIN)
                        .requestMatchers(HttpMethod.PUT, "/api/tarifas/ajustar/{idCuenta}").hasAuthority(AuthorityConstant._ADMIN)
                        .requestMatchers(HttpMethod.GET, "/api/cobros/total/{anio}/{mesIni}/{mesFin}").hasAuthority(AuthorityConstant._ADMIN)
                        .requestMatchers(HttpMethod.GET, "/api/viajes/monopatin/{id}/tiempoUso").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/viajes/monopatin/{id}/pausaAcumulada").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/viajes/monopatines-mas-viajes").hasAuthority(AuthorityConstant._ADMIN)
                        .requestMatchers(HttpMethod.GET, "/api/viajes/usuarios-mayor-uso").hasAuthority(AuthorityConstant._ADMIN)
                        .requestMatchers(HttpMethod.GET, "/api/viajes/uso").hasAnyAuthority(AuthorityConstant._USUARIO, AuthorityConstant._PREMIUM)
                        .requestMatchers("/api/usuarios/**").permitAll()
                        .requestMatchers("/api/monopatin/**").permitAll()
                        .requestMatchers("/api/tarifas/**").permitAll()
                        .requestMatchers("/api/cobros/**").permitAll()
                        .requestMatchers("/api/viajes/**").permitAll()
                        .requestMatchers("/api/mantenimiento/**").hasAuthority(AuthorityConstant._MANTENIMIENTO)
                        .requestMatchers("/api/billeteras/**").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic( Customizer.withDefaults() )
                .addFilterBefore( new JwtFilter( this.tokenProvider ), UsernamePasswordAuthenticationFilter.class );
        return http.build();
    }
}


