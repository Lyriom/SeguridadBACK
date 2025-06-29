package com.alerta.repositorio;

import com.alerta.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

//  Le damos a Spring una "puerta" para acceder a la tabla Usuario
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
}
