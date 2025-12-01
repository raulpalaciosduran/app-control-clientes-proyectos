package com.epsvial.app.service;

import com.epsvial.app.model.Pago;
import java.util.List;

/**
 * Interfaz que define las operaciones de negocio para la gestión de Pagos.
 * Es el contrato que debe implementar PagoServiceImpl.
 */
public interface PagoService {
    
    // Métodos CRUD estándar
    List<Pago> listarTodosLosPagos();
    Pago obtenerPagoPorId(Long id);
    void guardarPago(Pago pago);
    void eliminarPago(Long id);

    // Métodos específicos de negocio
    List<Pago> listarPagosPorProyecto(Long proyectoId);
    List<Pago> listarPagosPorProyectoOrdenados(Long proyectoId); // ✅ nuevo para ordenados por fecha
    Double totalPagosPorProyecto(Long proyectoId);
     Double totalPagos();
}
