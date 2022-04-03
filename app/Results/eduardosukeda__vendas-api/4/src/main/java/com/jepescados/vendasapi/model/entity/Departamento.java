package com.jepescados.vendasapi.model.entity;
 import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.transaction.Transactional;
import javax.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.jepescados.vendasapi.Request.FuncionarioRequest;
import com.jepescados.vendasapi.Request.Impl.FuncionarioRequestImpl;
import com.jepescados.vendasapi.DTO.Funcionario;
@Entity
@NoArgsConstructor
public class Departamento {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Integer id;

@Column(length = 150)
@NotEmpty
 private  String nome;

@Column(length = 30)
 private  String telefone;

@Transient
 private  List<Funcionario> funcionarios;

@JsonDeserialize(using = LocalDateTimeDeserializer.class)
@JsonSerialize(using = LocalDateTimeSerializer.class)
@Column(name = "dh_criacao", nullable = false, updatable = false)
 private  LocalDateTime dataCriacao;

@JsonDeserialize(using = LocalDateTimeDeserializer.class)
@JsonSerialize(using = LocalDateTimeSerializer.class)
@Column(name = "dh_alteracao")
 private  LocalDateTime dataAlteracao;

@Transient
 private FuncionarioRequest funcionariorequest = new FuncionarioRequestImpl();;


public String getTelefone(){
    return telefone;
}


public void setFuncionarios(List<Funcionario> funcionarios){
 funcionariorequest.setFuncionarios(funcionarios,this.id);
}



public void setDataCriacao(LocalDateTime dataCriacao){
    this.dataCriacao = dataCriacao;
}


@PrePersist
public void prePersist(){
    setDataCriacao(LocalDateTime.now());
}


public Integer getId(){
    return id;
}


public String getNome(){
    return nome;
}


public void setDataAlteracao(LocalDateTime dataAlteracao){
    this.dataAlteracao = dataAlteracao;
}


public LocalDateTime getDataAlteracao(){
    return dataAlteracao;
}


public void setNome(String nome){
    this.nome = nome;
}


public void setTelefone(String telefone){
    this.telefone = telefone;
}


public List<Funcionario> getFuncionarios(){
  this.funcionarios = funcionariorequest.getFuncionarios(this.id);
return this.funcionarios;
}


public void setId(Integer id){
    this.id = id;
}


public LocalDateTime getDataCriacao(){
    return dataCriacao;
}


}