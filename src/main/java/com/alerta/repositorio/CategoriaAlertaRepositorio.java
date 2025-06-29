package com.alerta.repositorio;
import com.alerta.entidades.CategoriaAlerta;
import com.alerta.entidades.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaAlertaRepositorio extends JpaRepository <CategoriaAlerta, Long>{

}
