package com.epsvial.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model; // Necesario si quieres pasar datos a la vista

@Controller
public class DashboardController {

    /**
     * Maneja las solicitudes GET a /dashboard.
     * Esta es la ruta a la que Spring Security redirige al usuario con rol ADMIN después del login exitoso.
     * @param model Objeto para pasar atributos a la vista (opcional).
     * @return El nombre de la plantilla HTML a renderizar (dashboard.html).
     */
    @GetMapping("/dashboard")
    public String viewDashboard(Model model) {
        // Aquí podrías agregar lógica para cargar datos específicos del administrador
        model.addAttribute("titulo", "Panel de Administración EPSVIAL");
        return "dashboard"; // Busca la plantilla en src/main/resources/templates/dashboard.html
    }
}
