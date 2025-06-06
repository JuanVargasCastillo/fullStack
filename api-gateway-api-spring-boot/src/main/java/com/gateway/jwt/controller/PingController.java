package com.gateway.jwt.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PingController {

    @GetMapping("/ping")
    public String ping() {
        return "✅ API pública funcionando correctamente";
    }
}
