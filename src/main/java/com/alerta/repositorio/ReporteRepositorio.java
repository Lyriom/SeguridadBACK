package com.alerta.repositorio;

import com.alerta.entidades.CategoriaAlerta;
import com.alerta.entidades.Reporte;
import com.alerta.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReporteRepositorio extends JpaRepository<Reporte, Long> {
    List<Reporte> findByCategoria(CategoriaAlerta categoria);


    List<Reporte> findByEstado(String estado);

    List<Reporte> findByUsuario(Usuario usuario);

}
