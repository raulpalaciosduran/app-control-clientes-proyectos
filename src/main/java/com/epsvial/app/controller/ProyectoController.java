package com.epsvial.app.controller;

import com.epsvial.app.model.Proyecto;
import com.epsvial.app.model.Cliente;
import com.epsvial.app.service.ProyectoService;
import com.epsvial.app.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/proyectos")
public class ProyectoController {

    @Autowired
    private ProyectoService service;

    @Autowired
    private ClienteService clienteService;

    // Listar proyectos
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("proyectos", service.listar());
        return "proyectos/listar";
    }

    // Formulario nuevo
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("proyecto", new Proyecto());
        model.addAttribute("clientes", clienteService.listar());
        return "proyectos/form";
    }

    // Guardar proyecto (POST /proyectos/guardar)
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Proyecto proyecto) {
        if (proyecto.getCliente() != null && proyecto.getCliente().getId() != null) {
            Cliente cliente = clienteService.buscarPorId(proyecto.getCliente().getId());
            if (cliente != null) {
                proyecto.setCliente(cliente);
            }
        }
        service.guardar(proyecto);
        return "redirect:/proyectos";
    }

    // Editar proyecto
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Proyecto proyecto = service.buscarPorId(id);
        if (proyecto == null) {
            return "redirect:/proyectos";
        }
        model.addAttribute("proyecto", proyecto);
        model.addAttribute("clientes", clienteService.listar());
        return "proyectos/form";
    }

    // Eliminar proyecto
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/proyectos";
    }
}
