package com.epsvial.app.controller;

import com.epsvial.app.model.Pago;
import com.epsvial.app.model.Proyecto;
import com.epsvial.app.service.PagoService;
import com.epsvial.app.service.ProyectoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/pagos")
public class PagoController {

    private final PagoService pagoService;
    private final ProyectoService proyectoService;

    @Autowired
    public PagoController(PagoService pagoService, ProyectoService proyectoService) {
        this.pagoService = pagoService;
        this.proyectoService = proyectoService;
    }

    // Listado de pagos
    @GetMapping
    public String listarPagos(Model model) {
        List<Pago> pagos = pagoService.listarTodosLosPagos();
        model.addAttribute("pagos", pagos);
        model.addAttribute("titulo", "Listado de Pagos");
        model.addAttribute("proyectos", proyectoService.listar());
        return "pagos/listar"; // ✅ vista correcta
    }

    // Formulario nuevo pago
    @GetMapping("/nuevo")
    public String nuevoPago(Model model) {
        Pago pago = new Pago();
        model.addAttribute("pago", pago);
        model.addAttribute("proyectos", proyectoService.listar());
        model.addAttribute("titulo", "Registrar Nuevo Pago");
        return "pagos/form"; // ✅ vista correcta
    }

    // Guardar pago
    @PostMapping("/guardar")
    public String guardarPago(@ModelAttribute("pago") Pago pago, RedirectAttributes redirect) {
        if (pago.getProyecto() != null && pago.getProyecto().getId() != null) {
            try {
                Proyecto proyecto = proyectoService.buscarPorId(pago.getProyecto().getId());
                pago.setProyecto(proyecto);
            } catch (RuntimeException e) {
                redirect.addFlashAttribute("error", "Error al buscar el proyecto asociado: " + e.getMessage());
                return "redirect:/pagos";
            }
        }
        pagoService.guardarPago(pago);
        redirect.addFlashAttribute("mensaje", "Pago guardado exitosamente.");
        return "redirect:/pagos";
    }

    // Editar pago
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        try {
            Pago pago = pagoService.obtenerPagoPorId(id);
            model.addAttribute("pago", pago);
            model.addAttribute("proyectos", proyectoService.listar());
            model.addAttribute("titulo", "Editar Pago");
            return "pagos/form"; // ✅ vista correcta
        } catch (RuntimeException e) {
            redirect.addFlashAttribute("error", "Pago no encontrado.");
            return "redirect:/pagos";
        }
    }

    // Eliminar pago
    @GetMapping("/eliminar/{id}")
    public String eliminarPago(@PathVariable Long id, RedirectAttributes redirect) {
        pagoService.eliminarPago(id);
        redirect.addFlashAttribute("mensaje", "Pago eliminado correctamente.");
        return "redirect:/pagos";
    }
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Long totalProyectos = proyectoService.contarProyectos();
        model.addAttribute("totalProyectos", totalProyectos);
        return "dashboard";
    }

}
