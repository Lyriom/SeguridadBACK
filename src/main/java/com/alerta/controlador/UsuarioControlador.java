package com.alerta.controlador;

import com.alerta.entidades.Usuario;
import com.alerta.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  //  Exponemos este controlador como una API REST
@RequestMapping("/api/usuarios")  //  Ruta base para este recurso
@CrossOrigin  // 🔓 Permitir peticiones desde el frontend (CORS)
public class UsuarioControlador {

    @Autowired  //  Inyectamos el repositorio para usarlo
    private UsuarioRepositorio usuarioRepositorio;

    @GetMapping  // 🟢 GET /api/usuarios
    public List<Usuario> listarUsuarios() {
        return usuarioRepositorio.findAll();
    }

    @PostMapping  // ➕ POST /api/usuarios
    public Usuario crearUsuario(@RequestBody Usuario u) {
        return usuarioRepositorio.save(u);
    }
}
