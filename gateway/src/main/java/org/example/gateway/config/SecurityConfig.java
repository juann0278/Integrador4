package org.example.gateway.config;

import org.example.gateway.security.AuthotityConstant;
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
                        //FALTA COMPLETAR A QUIEN SE DA PERMISO EN TODOS LOS REQUEST
                        //Microservicio Cuenta
                            //Billetera
                        .requestMatchers(HttpMethod.DELETE, "/api/billeteras/{billeteraId}")
                        .requestMatchers(HttpMethod.GET, "/api/billeteras/{billeteraId}/saldo")
                        .requestMatchers(HttpMethod.GET, "/api/billeteras/{billeteraId/estado-cuenta")
                        .requestMatchers(HttpMethod.GET, "/api/billeteras/{billeteraId}/premium")
                        .requestMatchers(HttpMethod.GET, "/api/billeteras/{billeteraId}/usuarios")
                        .requestMatchers(HttpMethod.PUT, "api/billeteras/{billeteraId}/estado-cuenta")
                        .requestMatchers(HttpMethod.PUT, "api/billeteras/{billeteraId}/premium")
                        .requestMatchers(HttpMethod.POST, "api/billeteras")
                        .requestMatchers(HttpMethod.GET, "api/billeteras")
                            //Carga
                        .requestMatchers(HttpMethod.POST, "/api/cargas/billetera/{billeteraId}")
                            //Usuario
                        .requestMatchers(HttpMethod.GET, "/api/usuarios")
                        .requestMatchers(HttpMethod.GET, "/api/usuarios/{id}")
                        .requestMatchers(HttpMethod.POST, "/api/usuarios")
                        .requestMatchers(HttpMethod.PUT, "/api/usuarios/{id}")
                        .requestMatchers(HttpMethod.DELETE, "/api/usuarios/{id}")
                        .requestMatchers(HttpMethod.PUT, "/api/usuarios/{id}/billetera")
                        .requestMatchers(HttpMethod.GET, "/api/usuarios/{id}/rol")
                        .requestMatchers(HttpMethod.GET, "/api/usuarios/rol/{rol}")
                        .requestMatchers(HttpMethod.GET, "/api/usuarios/relacionados/{usuarioId}")
                        //Microservicio Mantenimiento
                        .requestMatchers(HttpMethod.POST, "/api/mantenimiento")
                        .requestMatchers(HttpMethod.GET, "/api/mantenimiento")
                        .requestMatchers(HttpMethod.GET, "/api/mantenimiento/{id}")
                        .requestMatchers(HttpMethod.DELETE, "/api/mantenimiento/{id}")
                        .requestMatchers(HttpMethod.PUT, "/api/mantenimiento/{id}")
                        .requestMatchers(HttpMethod.POST, "/api/mantenimiento/monopatin/{idMonopatin}")
                        //Microservicio Monopatin
                        .requestMatchers(HttpMethod.GET, "/api/monopatin")
                        .requestMatchers(HttpMethod.GET, "/api/monopatin/{id}")
                        .requestMatchers(HttpMethod.POST, "/api/monopatin")
                        .requestMatchers(HttpMethod.DELETE, "/api/monopatin/{id}")
                        .requestMatchers(HttpMethod.PUT, "/api/monopatin/{id}")
                        .requestMatchers(HttpMethod.GET, "/api/monopatin/monopatinesCercanos/longitud/{longitud}/latitud/{latitud}")
                        .requestMatchers(HttpMethod.GET, "/api/monopatin/kms")
                        .requestMatchers(HttpMethod.PUT, "/api/mantenimiento/{id}")
                        .requestMatchers(HttpMethod.GET, "/api/monopatin/usoConPausa")
                        .requestMatchers(HttpMethod.POST, "/api/monopatin/distancia-total")
                        //Microservicio Tarifa
                            //Tarifa
                        .requestMatchers(HttpMethod.GET, "/api/tarifas")
                        .requestMatchers(HttpMethod.POST, "/api/tarifas")
                        .requestMatchers(HttpMethod.PUT, "/api/tarifas/{id}")
                        .requestMatchers(HttpMethod.DELETE, "/api/tarifas/{id}")
                        .requestMatchers(HttpMethod.PUT, "/api/tarifas/ajustar/{idCuenta}")
                            //Cobro
                        .requestMatchers(HttpMethod.GET, "/api/cobros")
                        .requestMatchers(HttpMethod.POST, "/api/cobros")
                        .requestMatchers(HttpMethod.GET, "/api/cobros/total/{anio}/{mesIni}/{mesFin}")
                        //Microservicio Viaje
                        .requestMatchers(HttpMethod.GET, "/api/viajes")
                        .requestMatchers(HttpMethod.POST, "/api/viajes")
                        .requestMatchers(HttpMethod.DELETE, "/api/viajes/{id}")
                        .requestMatchers(HttpMethod.PUT, "/api/viajes/{id}")
                        .requestMatchers(HttpMethod.GET, "/api/viajes/monopatin/{id}/tiempoUso")
                        .requestMatchers(HttpMethod.GET, "/api/viajes/monopatin/{id}/pausaAcumulada")
                        .requestMatchers(HttpMethod.GET, "/api/viajes/monopatines-mas-viajes")
                        .requestMatchers(HttpMethod.GET, "/api/viajes/usuarios-mayor-uso")
                        .requestMatchers(HttpMethod.GET, "/api/viajes/uso")
                        .anyRequest().authenticated()
                )
                .httpBasic( Customizer.withDefaults() )
                .addFilterBefore( new JwtFilter( this.tokenProvider ), UsernamePasswordAuthenticationFilter.class );
        return http.build();
    }
    }

}
