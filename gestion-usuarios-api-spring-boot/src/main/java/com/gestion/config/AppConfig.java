package com.gestion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Aquí puedes agregar más @Bean si lo necesitas, como mappers o CORS globales
    // ✅ 3. ¿Qué hace esta clase?
    // Marca la clase como de configuración de Spring con @Configuration.

    // Expone un PasswordEncoder como un @Bean, para ser inyectado automáticamente donde lo necesites.


}
