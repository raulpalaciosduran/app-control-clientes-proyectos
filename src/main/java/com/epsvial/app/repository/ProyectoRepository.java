package com.epsvial.app.repository;

import com.epsvial.app.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
    List<Proyecto> findByClienteId(Long clienteId);
    List<Proyecto> findByNombreContainingIgnoreCase(String nombre);
    List<Proyecto> findByEstado(String estado);
    Page<Proyecto> findByClienteId(Long clienteId, Pageable pageable);

}
