package com.alerta.entidades;
import jakarta.persistence.*;

@Entity  //  Marca esta clase como una "entidad JPA", o sea: se convertirá en una tabla
public class Usuario {

    @Id  //  Indica que este campo es la clave primaria (PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //  Le dice a la BD que genere automáticamente el ID (autoincremental)
    private Long id;

    private String nombre;     //  Nombre del usuario
    private String cedula;     //  Cédula o ID nacional
    private String celular;    //  Teléfono del usuario
    private String tipo;       //  "ciudadano" o "autoridad"

    //  Constructor vacío
    public Usuario() {}

    //  Getters y Setters para acceder y modificar los campos
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

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

