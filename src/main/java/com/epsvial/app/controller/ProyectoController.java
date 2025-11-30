package com.epsvial.app.controller;

import com.epsvial.app.model.Proyecto;
import com.epsvial.app.model.Cliente;
import com.epsvial.app.service.ProyectoService;
import com.epsvial.app.service.ClienteService; // 👈 CORRECTO
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
    private ClienteService clienteService; // 👈 CORRECTO

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("proyectos", service.listar());
        return "proyectos/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("proyecto", new Proyecto());
        model.addAttribute("clientes", clienteService.listar());
        return "proyectos/form";
    }

   @PostMapping
    public String guardar(@ModelAttribute Proyecto proyecto) {
        // Recuperar el cliente por id antes de guardar
        Cliente cliente = clienteService.buscarPorId(proyecto.getCliente().getId());
        proyecto.setCliente(cliente);

        service.guardar(proyecto);
        return "redirect:/proyectos";
    }


    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("proyecto", service.buscarPorId(id));
        model.addAttribute("clientes", clienteService.listar());
        return "proyectos/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/proyectos";
    }
}
