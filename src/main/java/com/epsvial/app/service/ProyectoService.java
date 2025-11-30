package com.epsvial.app.service;

import com.epsvial.app.model.Proyecto;
import com.epsvial.app.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProyectoService {
    @Autowired
    private ProyectoRepository repo;

    public List<Proyecto> listar() { return repo.findAll(); }
    public Proyecto guardar(Proyecto p) { return repo.save(p); }
    public Proyecto buscarPorId(Long id) { return repo.findById(id).orElse(null); }
    public void eliminar(Long id) { repo.deleteById(id); }
}
