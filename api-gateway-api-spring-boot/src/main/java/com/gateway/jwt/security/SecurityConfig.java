package com.gateway.jwt.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.http.HttpMethod; // Asegúrate de importar esto arriba

import static com.gateway.jwt.security.PublicRoutes.*; //importa las rutas publicas de jwt
import static com.gateway.redireccion.gestion.GestionPublicRoutes.*; //importa las rutas publicas de API Gateway
import static com.gateway.redireccion.productos.ProductosPublicRoutes.*; //importa las rutas publicas de API Productos
import static com.gateway.redireccion.clientes.ClientesPublicRoutes.*; //importa las rutas publicas de API Clientes

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth

                // URL públicas JWT
                .requestMatchers(HttpMethod.POST, PUBLIC_POST).permitAll() // rutas publicas POST de PublicRoutes de JWT
                .requestMatchers(HttpMethod.GET, PUBLIC_GET).permitAll() // rutas publicas GET de PublicRoutes de JWT

                // URL públicas API Gestion
                .requestMatchers(HttpMethod.GET, GESTION_PUBLIC_GET).permitAll()   // lista pública api GESTION GET

                // URL públicas API Productos
                .requestMatchers(HttpMethod.GET, PRODUCTOS_PUBLIC_GET).permitAll()   // lista pública api Productos GET

                // URL públicas API Clientes
                .requestMatchers(HttpMethod.GET, CLIENTES_PUBLIC_GET).permitAll()   // lista pública api Clientes GET
                
                // Otras URL Token obligatorio
                .anyRequest().authenticated()

            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
