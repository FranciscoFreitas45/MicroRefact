package com.jepescados.vendasapi.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.jepescados.vendasapi.DTO.Funcionario;
import com.jepescados.vendasapi.Request.FuncionarioRequest;
public class FuncionarioRequestImpl implements FuncionarioRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setFuncionarios(List<Funcionario> funcionarios,Integer id){
 restTemplate.put("http://1/Departamento/{id}/Funcionario/setFuncionarios",funcionarios,id);
 return ;
}


public List<Funcionario> getFuncionarios(Integer id){
 List<Funcionario> aux = restTemplate.getForObject("http://1/Departamento/{id}/Funcionario/getFuncionarios",List<Funcionario>.class,id);
return aux;
}


}