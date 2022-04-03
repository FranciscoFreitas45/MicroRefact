package com.jepescados.vendasapi.rest.dto;
 import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import com.jepescados.vendasapi.model.entity.Departamento;
public class DepartamentoDto {

 private  Integer id;

 private  String nome;

 private  String telefone;

 private  LocalDateTime dataCriacao;

 private  LocalDateTime dataAlteracao;

public DepartamentoDto(Departamento dept) {
    this.id = dept.getId();
    this.nome = dept.getNome();
    this.telefone = dept.getTelefone();
    this.dataCriacao = dept.getDataCriacao();
    this.dataAlteracao = dept.getDataAlteracao();
}
public String getTelefone(){
    return telefone;
}


public LocalDateTime getDataAlteracao(){
    return dataAlteracao;
}


public List<DepartamentoDto> converter(List<Departamento> depts){
    return depts.stream().map(DepartamentoDto::new).collect(Collectors.toList());
}


public LocalDateTime getDataCriacao(){
    return dataCriacao;
}


public Integer getId(){
    return id;
}


public String getNome(){
    return nome;
}


}