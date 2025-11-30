package com.epsvial.app.repository;

import com.epsvial.app.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
    List<Proyecto> findByClienteId(Long clienteId);
}
