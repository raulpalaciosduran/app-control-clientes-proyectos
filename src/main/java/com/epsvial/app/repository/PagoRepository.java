package com.epsvial.app.repository;

import com.epsvial.app.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PagoRepository extends JpaRepository<Pago, Long> {
    List<Pago> findByProyectoId(Long proyectoId);
}
