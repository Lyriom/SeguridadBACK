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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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

    // ✅ Obtener todos los reportes
    @GetMapping
    public List<Reporte> listarReportes() {
        return reporteRepositorio.findAll();
    }

    // ✅ Obtener por categoría
    @GetMapping("/categoria/{id}")
    public List<Reporte> buscarCategoria(@PathVariable long id) {
        CategoriaAlerta cat = new CategoriaAlerta();
        cat.setId(id);
        return reporteRepositorio.findByCategoria(cat);
    }

    // ✅ Obtener por estado
    @GetMapping("/estado/{estado}")
    public List<Reporte> buscarestado(@PathVariable String estado) {
        return reporteRepositorio.findByEstado(estado);
    }

    // ✅ Obtener por usuario
    @GetMapping("/usuario/{id}")
    public List<Reporte> buscarPorUsuario(@PathVariable Long id) {
        Usuario user = new Usuario();
        user.setId(id);
        return reporteRepositorio.findByUsuario(user);
    }

    // ✅ ACTUALIZAR ESTADO (CORREGIDO)
    @PutMapping("/{id}/estado")
    public ResponseEntity<Reporte> actualizarEstado(@PathVariable Long id, @RequestBody Map<String, String> estado) {
        Reporte r = reporteRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));
        r.setEstado(estado.get("estado"));
        return ResponseEntity.ok(reporteRepositorio.save(r));
    }

    // ✅ Crear nuevo reporte
    @PostMapping
    public Reporte crearReporte(@RequestBody Reporte reporte) {
        Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(reporte.getUsuario().getId());

        if (reporte.getCategoria() == null) {
            throw new RuntimeException("Debe especificar una categoría válida para el reporte");
        }

        if (usuarioOptional.isPresent()) {
            reporte.setUsuario(usuarioOptional.get());

            CategoriaAlerta categoriaCompleta = categoriaAlertaRepositorio.findById(reporte.getCategoria().getId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            reporte.setCategoria(categoriaCompleta);

            Reporte guardado = reporteRepositorio.save(reporte);
            notificacionService.enviarAlertaWhatsApp(guardado);
            return guardado;

        } else {
            throw new RuntimeException("Usuario con ID " + reporte.getUsuario().getId() + " no encontrado.");
        }
    }
}

