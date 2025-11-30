package com.epsvial.app.controller;

import com.epsvial.app.model.Pago;
import com.epsvial.app.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.epsvial.app.model.Proyecto;
import com.epsvial.app.service.ProyectoService;

@Controller
@RequestMapping("/pagos")
public class PagoController {
    @Autowired
    private PagoService service;

    @Autowired
    private ProyectoService proyectoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pagos", service.listar());
        return "pagos/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("pago", new Pago());
        model.addAttribute("proyectos", proyectoService.listar());
        return "pagos/form";
    }

    @PostMapping
    public String guardar(@ModelAttribute Pago pago) {
        Proyecto proyecto = proyectoService.buscarPorId(pago.getProyecto().getId());
        pago.setProyecto(proyecto);
        service.guardar(pago);
        return "redirect:/pagos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/pagos";
    }
}
