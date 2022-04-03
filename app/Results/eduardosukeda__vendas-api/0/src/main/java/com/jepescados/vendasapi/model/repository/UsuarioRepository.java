package com.jepescados.vendasapi.model.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jepescados.vendasapi.model.entity.Usuario;
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{


public boolean existsByLogin(String login)
;

public Optional<Usuario> findByLogin(String login)
;

}