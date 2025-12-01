package com.epsvial.app.controller;

import com.epsvial.app.model.Cliente;
import com.epsvial.app.service.ClienteService;
import com.epsvial.app.service.ProyectoService;
import com.epsvial.app.service.PagoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    private final ClienteService clienteService;
    private final ProyectoService proyectoService;
    private final PagoService pagoService;

    // Inyección por Constructor
    public DashboardController(ClienteService clienteService,
                               ProyectoService proyectoService,
                               PagoService pagoService) {
        this.clienteService = clienteService;
        this.proyectoService = proyectoService;
        this.pagoService = pagoService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // --- Clientes ---
        List<Cliente> clientes = clienteService.listar();
        int totalClientes = clientes.size();

        // --- Proyectos ---
        Long totalProyectos = proyectoService.contarProyectos();

        // --- Pagos ---
        Double totalPagos = pagoService.totalPagos(); // 👈 asegúrate de tener este método en tu servicio

        // Atributos para la vista
        model.addAttribute("titulo", "Dashboard");
        model.addAttribute("clientes", clientes);
        model.addAttribute("totalClientes", totalClientes);
        model.addAttribute("totalProyectos", totalProyectos);
        model.addAttribute("totalPagos", totalPagos);

        return "dashboard";
    }
}
