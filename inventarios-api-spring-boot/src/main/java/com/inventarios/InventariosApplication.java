package com.inventarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Esto activa el escaneo de todos los subpaquetes
public class InventariosApplication {
    public static void main(String[] args) {
        SpringApplication.run(InventariosApplication.class, args);
    }
}
