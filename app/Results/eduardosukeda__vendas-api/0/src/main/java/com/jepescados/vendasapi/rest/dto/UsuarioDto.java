package com.jepescados.vendasapi.rest.dto;
 import java.time.LocalDateTime;
import com.jepescados.vendasapi.model.entity.Departamento;
import com.jepescados.vendasapi.model.entity.Usuario;
public class UsuarioDto {

 private  Integer id;

 private  String nome;

 private  String email;

 private  String telefone;

 private  String login;

 private  LocalDateTime dataCriacao;

 private  LocalDateTime dataAlteracao;

public UsuarioDto(Usuario user) {
    this.id = user.getId();
    this.nome = user.getNome();
    this.email = user.getEmail();
    this.telefone = user.getTelefone();
    this.login = user.getLogin();
    this.dataCriacao = user.getDataCriacao();
    this.dataAlteracao = user.getDataAlteracao();
}
public String getLogin(){
    return login;
}


public String getTelefone(){
    return telefone;
}


public LocalDateTime getDataAlteracao(){
    return dataAlteracao;
}


public String getEmail(){
    return email;
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