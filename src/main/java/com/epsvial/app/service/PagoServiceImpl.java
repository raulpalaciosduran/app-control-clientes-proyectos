package com.epsvial.app.service;

import com.epsvial.app.model.Pago;
import com.epsvial.app.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoServiceImpl implements PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Override
    public List<Pago> listarTodosLosPagos() {
        return pagoRepository.findAll();
    }

    @Override
    public Pago obtenerPagoPorId(Long id) {
        return pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));
    }

    @Override
    public void guardarPago(Pago pago) {
        pagoRepository.save(pago);
    }

    @Override
    public void eliminarPago(Long id) {
        pagoRepository.deleteById(id);
    }

    @Override
    public List<Pago> listarPagosPorProyecto(Long proyectoId) {
        return pagoRepository.findByProyectoId(proyectoId);
    }

    @Override
    public List<Pago> listarPagosPorProyectoOrdenados(Long proyectoId) {
        return pagoRepository.findByProyectoIdOrderByFechaPagoDesc(proyectoId);
    }

    @Override
    public Double totalPagosPorProyecto(Long proyectoId) {
        return pagoRepository.totalPorProyecto(proyectoId);
    }
    public Double totalPagos() {
    return pagoRepository.findAll()
                         .stream()
                         .mapToDouble(Pago::getMonto)
                         .sum();
    }

}
