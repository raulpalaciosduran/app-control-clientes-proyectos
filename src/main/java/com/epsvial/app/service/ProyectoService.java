package com.epsvial.app.service;

import com.epsvial.app.model.Proyecto;
import com.epsvial.app.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Servicio de Proyecto: Contiene la lógica de negocio para la entidad Proyecto.
 * Usa los nombres de métodos 'listar()' y 'buscarPorId()'.
 */
@Service
public class ProyectoService {

    @Autowired
    private ProyectoRepository repo;

    // Métodos CRUD básico
    
    // MÉTODO REQUERIDO: Lista todos los proyectos.
    public List<Proyecto> listar() {
        return repo.findAll();
    }

    public Proyecto guardar(Proyecto p) {
        return repo.save(p);
    }

    /**
     * Busca un proyecto por ID. Si no se encuentra, lanza una RuntimeException.
     * Esta es la forma preferida para evitar NullPointerExceptions en los controladores.
     */
    public Proyecto buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(
            // Lanza una excepción específica que el controlador puede capturar
            () -> new RuntimeException("Proyecto no encontrado con ID: " + id) 
        );
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    // Métodos adicionales (Mantenidos)
    public List<Proyecto> listarPorCliente(Long clienteId) {
        return repo.findByClienteId(clienteId);
    }

    public Page<Proyecto> listarPorCliente(Long clienteId, Pageable pageable) {
        return repo.findByClienteId(clienteId, pageable);
    }

    public List<Proyecto> buscarPorNombre(String nombre) {
        return repo.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Proyecto> buscarPorEstado(String estado) {
        return repo.findByEstado(estado);
    }
    
    public Long contarProyectos() {
        return (long) repo.count();
    }

}