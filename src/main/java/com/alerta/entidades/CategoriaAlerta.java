package com.alerta.entidades;
import jakarta.persistence.*;
@Entity
public class CategoriaAlerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tiposuceso;
    public CategoriaAlerta() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTiposuceso() {
        return tiposuceso;
    }

    public void setTiposuceso(String tiposuceso) {
        this.tiposuceso = tiposuceso;
    }






}
