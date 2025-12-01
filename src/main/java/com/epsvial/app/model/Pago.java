package com.epsvial.app.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Entidad que representa un pago asociado a un proyecto.
 * No utiliza Lombok.
 */
@Entity
@Table(name = "pagos")
public class Pago {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) 
    private Double monto;
    
    // ✅ Mapeo explícito a la columna 'fecha'
    @Column(name = "fecha", nullable = false)
    private LocalDate fechaPago;

    @Column(length = 255)
    private String descripcion; // ✅ agregado para coincidir con form.html

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "proyecto_id", nullable = false) 
    private Proyecto proyecto;

    // ==========================================================
    // CONSTRUCTORES
    // ==========================================================
    public Pago() {
    }

    public Pago(Long id, Double monto, LocalDate fechaPago, String descripcion, Proyecto proyecto) {
        this.id = id;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.descripcion = descripcion;
        this.proyecto = proyecto;
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

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    // ==========================================================
    // MÉTODOS DE OBJETO
    // ==========================================================
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass() || id == null) return false;
        Pago pago = (Pago) o;
        return Objects.equals(id, pago.id);
    }

    @Override
    public int hashCode() {
        return id != null ? Objects.hash(id) : 0;
    }

    @Override
    public String toString() {
        String proyectoNombre = (proyecto != null) ? proyecto.getNombre() : "N/A";
        return "Pago{" +
                "id=" + id +
                ", monto=" + monto +
                ", fechaPago=" + fechaPago +
                ", descripcion='" + descripcion + '\'' +
                ", proyectoNombre='" + proyectoNombre + '\'' +
                '}';
    }
}
