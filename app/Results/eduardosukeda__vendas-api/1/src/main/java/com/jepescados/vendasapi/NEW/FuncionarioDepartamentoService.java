package com.jepescados.vendasapi.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.jepescados.vendasapi.model.repository.FuncionarioRepository;
import com.jepescados.vendasapi.model.entity.Funcionario;
@Service
public class FuncionarioDepartamentoService {

@Autowired
 private FuncionarioRepository funcionariorepository;


public void setFuncionarios(Integer id,List<Funcionario> funcionarios){
funcionariorepository.setFuncionarios(id,funcionarios);
}


public List<Funcionario> getFuncionarios(Integer id){
return funcionariorepository.getFuncionarios(id);
}


}