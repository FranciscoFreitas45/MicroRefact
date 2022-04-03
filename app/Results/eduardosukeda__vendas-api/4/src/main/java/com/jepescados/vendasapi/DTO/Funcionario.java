package com.jepescados.vendasapi.DTO;
 import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.jepescados.vendasapi.Request.UsuarioRequest;
import com.jepescados.vendasapi.Request.Impl.UsuarioRequestImpl;
import com.jepescados.vendasapi.DTO.Usuario;
import com.jepescados.vendasapi.Request.DepartamentoRequest;
import com.jepescados.vendasapi.Request.Impl.DepartamentoRequestImpl;
import com.jepescados.vendasapi.DTO.Departamento;
import com.jepescados.vendasapi.Request.EnderecoRequest;
import com.jepescados.vendasapi.Request.Impl.EnderecoRequestImpl;
import com.jepescados.vendasapi.DTO.Endereco;
public class Funcionario {

 private  Integer id;

 private  Usuario usuario;

 private  Departamento departamento;

 private  Endereco endereco;

 private  String funcao;

 private  byte[] foto;

 private  LocalDateTime dataCriacao;

 private  LocalDateTime dataAlteracao;

 private Integer id;

 private Integer id;

 private Integer id;


}