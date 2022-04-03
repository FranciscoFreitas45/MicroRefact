package com.jepescados.vendasapi.DTO;
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
public class Departamento {

 private  Integer id;

 private  String nome;

 private  String telefone;

 private  List<Funcionario> funcionarios;

 private  LocalDateTime dataCriacao;

 private  LocalDateTime dataAlteracao;


public String getTelefone(){
    return telefone;
}


public Integer getId(){
    return id;
}


public String getNome(){
    return nome;
}


public LocalDateTime getDataAlteracao(){
    return dataAlteracao;
}


public List<Funcionario> getFuncionarios(){
    return funcionarios;
}


public LocalDateTime getDataCriacao(){
    return dataCriacao;
}


}