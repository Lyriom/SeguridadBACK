package com.alerta.entidades;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity  //  Indica que esta clase será una tabla en la BD
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private Double latitud;            // 📍 Latitud del lugar
    private Double longitud;           // 📍 Longitud del lugar
    private LocalDateTime fecha = LocalDateTime.now();  // 🕒 Fecha automática al crear

    private String estado = "pendiente"; // ✅ Estado del reporte (por defecto es "pendiente")

    @ManyToOne  //  Muchos reportes pueden pertenecer a un solo usuario
    @JoinColumn(name = "usuario_id")  //  Clave foránea en la tabla reporte
    private Usuario usuario;




    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private CategoriaAlerta categoria;

    public Reporte() {}

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public CategoriaAlerta getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaAlerta categoria) {
        this.categoria = categoria;
    }
}
