package com.jepescados.vendasapi.model.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import com.jepescados.vendasapi.model.entity.Funcionario;
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>{


public void setFuncionarios(Integer id,List<Funcionario> funcionarios);

public List<Funcionario> getFuncionarios(Integer id);

}