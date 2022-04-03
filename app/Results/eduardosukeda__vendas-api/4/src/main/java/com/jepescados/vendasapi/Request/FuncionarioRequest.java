package com.jepescados.vendasapi.Request;
import com.jepescados.vendasapi.DTO.Funcionario;
public interface FuncionarioRequest {

   public void setFuncionarios(List<Funcionario> funcionarios,Integer id);
   public List<Funcionario> getFuncionarios(Integer id);
}