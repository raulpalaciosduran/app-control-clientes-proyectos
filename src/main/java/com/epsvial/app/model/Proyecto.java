package com.epsvial.app.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Entidad JPA que representa un Proyecto.
 * Define la relación bidireccional con Pago (OneToMany) y ManyToOne con Cliente.
 */
@Entity
@Table(name = "proyectos")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // Aseguramos que el nombre sea obligatorio
    private String nombre;

    @Column(nullable = false)
    private Double presupuesto;
    
    @Column(nullable = false)
    private String estado; // Estado del proyecto (Ej: 'Activo', 'Finalizado', 'Pausado')

    // Relación ManyToOne con Cliente: Un proyecto pertenece a un cliente
    // Usamos FetchType.EAGER ya que probablemente siempre necesitemos saber qué cliente es.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id", nullable = false) // FK en la tabla proyectos
    private Cliente cliente;

    // Relación OneToMany con Pago: Un proyecto tiene muchos pagos
    @OneToMany(
        mappedBy = "proyecto", // Nombre del campo en la clase Pago (la parte ManyToOne)
        cascade = CascadeType.ALL, // Las operaciones de persistencia se propagan
        orphanRemoval = true, // Elimina los pagos huérfanos si se quitan de la lista
        fetch = FetchType.LAZY // Recomendado para colecciones: carga solo cuando se acceda a getPagos()
    )
    private List<Pago> pagos = new ArrayList<>();

    // ==========================================================
    // CONSTRUCTORES
    // ==========================================================
    
    // Constructor por defecto (necesario para JPA)
    public Proyecto() {}

    // Constructor para inicialización rápida (sin lista de pagos)
    public Proyecto(String nombre, Double presupuesto, String estado, Cliente cliente) {
        this.nombre = nombre;
        this.presupuesto = presupuesto;
        this.estado = estado;
        this.cliente = cliente;
    }


    // ==========================================================
    // GETTERS Y SETTERS
    // ==========================================================
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPresupuesto() {
        return presupuesto;
    }
    public void setPresupuesto(Double presupuesto) {
        this.presupuesto = presupuesto;
    }
    
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Pago> getPagos() {
        return pagos;
    }
    
    // NOTA: Se evita el setPagos para colecciones. Se usan métodos de ayuda.
    /*
    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }
    */

    // ==========================================================
    // MÉTODOS DE AYUDA (MEJORA DE LA RELACIÓN BIDIRECCIONAL)
    // ==========================================================

    /**
     * Añade un pago y gestiona la relación bidireccional, 
     * asignando este proyecto al objeto Pago.
     */
    public void addPago(Pago pago) {
        pagos.add(pago);
        pago.setProyecto(this);
    }

    /**
     * Elimina un pago y gestiona la relación bidireccional, 
     * anulando la referencia al proyecto en el objeto Pago.
     */
    public void removePago(Pago pago) {
        pagos.remove(pago);
        pago.setProyecto(null);
    }

    // ==========================================================
    // MÉTODOS DE OBJETO (Para un manejo correcto de JPA y colecciones)
    // ==========================================================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        // Solo comparamos si el ID existe y es igual (identidad de la entidad)
        if (o == null || getClass() != o.getClass() || id == null) return false;
        Proyecto proyecto = (Proyecto) o;
        return Objects.equals(id, proyecto.id);
    }

    @Override
    public int hashCode() {
        // Usamos el ID para generar el hash
        return id != null ? Objects.hash(id) : 0;
    }

    @Override
    public String toString() {
        String clienteNombre = (cliente != null) ? cliente.getNombre() : "N/A";
        return "Proyecto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", presupuesto=" + presupuesto +
                ", estado='" + estado + '\'' +
                ", clienteNombre='" + clienteNombre + '\'' +
                '}';
    }
}