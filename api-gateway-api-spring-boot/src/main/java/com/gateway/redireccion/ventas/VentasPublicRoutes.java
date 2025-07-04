package com.gateway.redireccion.ventas;


public class VentasPublicRoutes {
    public static final String[] VENTAS_PUBLIC_GET = {
        "/api/proxy/ventas",
        "/api/proxy/ventas/",
        "/api/proxy/ventas/hateoas/*",
        "/api/proxy/ventas/hateoas/cliente/*",
        "/api/proxy/ventas/hateoas"
    };

}
