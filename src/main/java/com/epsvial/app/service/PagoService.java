package com.epsvial.app.service;

import com.epsvial.app.model.Pago;
import com.epsvial.app.repository.PagoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PagoService {
    private final PagoRepository repo;

    public PagoService(PagoRepository repo) {
        this.repo = repo;
    }

    public List<Pago> listar() { return repo.findAll(); }
    public Pago guardar(Pago p) { return repo.save(p); }
    public Pago buscarPorId(Long id) { return repo.findById(id).orElse(null); }
    public void eliminar(Long id) { repo.deleteById(id); }

    public List<Pago> listarPorProyecto(Long proyectoId) {
        return repo.findByProyectoId(proyectoId);
    }

    public Double totalPorProyecto(Long proyectoId) {
        return repo.findByProyectoId(proyectoId)
                   .stream()
                   .mapToDouble(p -> p.getMonto() != null ? p.getMonto() : 0.0)
                   .sum();
    }
}
