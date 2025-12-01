package com.epsvial.app.repository;

import com.epsvial.app.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PagoRepository extends JpaRepository<Pago, Long> {

    // Listar todos los pagos de un proyecto por su ID
    List<Pago> findByProyectoId(Long proyectoId);

    // Listar pagos de un proyecto ordenados por el campo 'fechaPago' (más recientes primero)
    List<Pago> findByProyectoIdOrderByFechaPagoDesc(Long proyectoId);

    // Calcular el total de pagos de un proyecto directamente en BD
    @Query("SELECT SUM(p.monto) FROM Pago p WHERE p.proyecto.id = :proyectoId")
    Double totalPorProyecto(@Param("proyectoId") Long proyectoId);
}
