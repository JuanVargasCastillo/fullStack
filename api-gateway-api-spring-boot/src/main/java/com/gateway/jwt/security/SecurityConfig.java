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

import org.springframework.http.HttpMethod;

// Importa las rutas públicas de cada microservicio
import static com.gateway.jwt.security.PublicRoutes.*; // JWT
import static com.gateway.redireccion.gestion.GestionPublicRoutes.*; // Gestión
import static com.gateway.redireccion.productos.ProductosPublicRoutes.*; // Productos
import static com.gateway.redireccion.clientes.ClientesPublicRoutes.*; // Clientes
import static com.gateway.redireccion.envios.EnviosPublicRoutes.*; // Envios ✅ NUEVO
import static com.gateway.redireccion.inventarios.InventariosPublicRoutes.*;
import static com.gateway.redireccion.reportes.ReportesPublicRoutes.*;
import static com.gateway.redireccion.soporte.SoportePublicRoutes.*;
import static com.gateway.redireccion.vendedores.VendedoresPublicRoutes.*;
import static com.gateway.redireccion.ventas.VentasPublicRoutes.*;


@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth

                // Rutas públicas del microservicio de autenticación (JWT)
                .requestMatchers(HttpMethod.POST, PUBLIC_POST).permitAll()
                .requestMatchers(HttpMethod.GET, PUBLIC_GET).permitAll()

                // Rutas públicas de Gestión
                .requestMatchers(HttpMethod.GET, GESTION_PUBLIC_GET).permitAll()

                // Rutas públicas de Productos
                .requestMatchers(HttpMethod.GET, PRODUCTOS_PUBLIC_GET).permitAll()

                // Rutas públicas de Clientes
                .requestMatchers(HttpMethod.GET, CLIENTES_PUBLIC_GET).permitAll()

                // ✅ NUEVO: Rutas públicas de Envios
                .requestMatchers(HttpMethod.GET, ENVIOS_PUBLIC_GET).permitAll()
                
                .requestMatchers(HttpMethod.GET, INVENTARIOS_PUBLIC_GET).permitAll() // rutas públicas GET de Inventarios

                .requestMatchers(HttpMethod.GET, REPORTES_PUBLIC_GET).permitAll() // rutas públicas GET de Inventarios

                .requestMatchers(HttpMethod.GET, SOPORTE_PUBLIC_GET).permitAll() // rutas públicas GET de Inventarios

                .requestMatchers(HttpMethod.GET, VENDEDORES_PUBLIC_GET).permitAll() // rutas públicas GET de Inventarios

                .requestMatchers(HttpMethod.GET, VENTAS_PUBLIC_GET).permitAll() // rutas públicas GET de Inventarios


                // Todo lo demás requiere autenticación
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
