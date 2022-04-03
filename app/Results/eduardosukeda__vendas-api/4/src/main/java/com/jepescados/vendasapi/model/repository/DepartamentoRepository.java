package com.jepescados.vendasapi.model.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import com.jepescados.vendasapi.model.entity.Departamento;
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer>{


public boolean existsByNome(String nome)
;

}