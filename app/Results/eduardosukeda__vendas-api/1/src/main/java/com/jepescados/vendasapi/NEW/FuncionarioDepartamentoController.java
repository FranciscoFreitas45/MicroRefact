package com.jepescados.vendasapi.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.jepescados.vendasapi.model.entity.Funcionario;
@RestController
@CrossOrigin
public class FuncionarioDepartamentoController {

@Autowired
 private FuncionarioDepartamentoService funcionariodepartamentoservice;


@PutMapping
("/Departamento/{id}/Funcionario/setFuncionarios")
public void setFuncionarios(@PathVariable(name="id") Integer id,@RequestBody List<Funcionario> funcionarios){
funcionariodepartamentoservice.setFuncionarios(id,funcionarios);
}


@GetMapping
("/Departamento/{id}/Funcionario/getFuncionarios")
public List<Funcionario> getFuncionarios(@PathVariable(name="id") Integer id){
return funcionariodepartamentoservice.getFuncionarios(id);
}


}