package com.alerta.controlador;
import com.alerta.entidades.CategoriaAlerta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import  com.alerta.repositorio.CategoriaAlertaRepositorio;
import java.util.List;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin

public class CategoriaAlertaControlador {



    @Autowired
    private CategoriaAlertaRepositorio categoriaAlertaRepositorio;

    @GetMapping
    public List<CategoriaAlerta> obtenerCategorias() {
        return categoriaAlertaRepositorio.findAll();
    }
    @PostMapping
    public CategoriaAlerta crear(@RequestBody CategoriaAlerta cat){
        return categoriaAlertaRepositorio.save(cat);
    }


}
