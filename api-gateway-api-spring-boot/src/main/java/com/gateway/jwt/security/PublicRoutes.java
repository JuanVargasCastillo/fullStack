package com.gateway.jwt.security;

public class PublicRoutes {

    // Rutas públicas para GET
    public static final String[] PUBLIC_GET = {
        "/api/ping"
    };


    // Rutas públicas para POST
    public static final String[] PUBLIC_POST = {
        "/api/auth/login",
        // puedes agregar otras si es necesario
    };

}
