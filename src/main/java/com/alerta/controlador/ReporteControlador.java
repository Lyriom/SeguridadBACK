package com.alerta.controlador;

import com.alerta.entidades.CategoriaAlerta;
import com.alerta.entidades.Reporte;
import com.alerta.entidades.Usuario;
import com.alerta.repositorio.CategoriaAlertaRepositorio;
import com.alerta.repositorio.ReporteRepositorio;
import com.alerta.repositorio.UsuarioRepositorio;
import com.alerta.servicio.NotificacionService;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin
public class ReporteControlador {

    @Autowired
    private ReporteRepositorio reporteRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private NotificacionService notificacionService;
    @Autowired
    private CategoriaAlertaRepositorio categoriaAlertaRepositorio;


    @GetMapping
    public List<Reporte> listarReportes() {
        return reporteRepositorio.findAll();
    }

    @ManyToOne
    @JoinColumn (name = "usurioid")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "sucesoid")
    private CategoriaAlerta categoriaAlerta;

    @GetMapping ("/categoria/{id}")
    public  List<Reporte> buscarCategoria(@PathVariable long id){
        CategoriaAlerta cat=  new CategoriaAlerta();
        cat.setId(id);
        return reporteRepositorio.findByCategoria(cat);

    }
    @GetMapping("/estado/{estado}")
    public List<Reporte> buscarestado(@PathVariable String estado){
        return reporteRepositorio.findByEstado(estado);
    }





    @PostMapping
    public Reporte crearReporte(@RequestBody Reporte reporte) {
        // 1. Verificamos que el usuario exista
        Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(reporte.getUsuario().getId());

        // Verificamos que la categoría exista
        if (reporte.getCategoria() == null) {
            throw new RuntimeException("Debe especificar una categoría válida para el reporte");
        }

        if (usuarioOptional.isPresent()) {
            // 2. Asignamos el objeto usuario al reporte
            reporte.setUsuario(usuarioOptional.get());

            // 3. Asegurarnos de que la categoría completa se carga
            CategoriaAlerta categoriaCompleta = categoriaAlertaRepositorio.findById(reporte.getCategoria().getId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            reporte.setCategoria(categoriaCompleta);

            // 4. Guardamos el reporte
            Reporte guardado = reporteRepositorio.save(reporte);

            // 5. Enviar notificación con los datos reales
            notificacionService.enviarAlertaWhatsApp(guardado);

            return guardado;
        } else {
            throw new RuntimeException("Usuario con ID " + reporte.getUsuario().getId() + " no encontrado.");
        }
    }

}

