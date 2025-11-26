package com.epsvial.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public String health() {
        return "EPS Vial App corriendo ✅";
    }

    @GetMapping("/epsvial-check")
    public String check() {
        return "EPS Vial funcionando en puerto 8082 🚀";
    }
}
