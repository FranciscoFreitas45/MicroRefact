package com.jepescados.vendasapi.model.repository;
 import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jepescados.vendasapi.model.entity.Cliente;
import com.jepescados.vendasapi.model.entity.Usuario;
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{


public Optional<Cliente> findByUsuarioId(Integer id)
;

}