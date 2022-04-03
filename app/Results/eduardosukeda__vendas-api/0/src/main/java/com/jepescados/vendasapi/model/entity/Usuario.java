package com.jepescados.vendasapi.model.entity;
 import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
public class Usuario {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Integer id;

@Column(nullable = false, length = 150)
@NotEmpty
 private  String nome;

@Column(nullable = false, length = 150)
@NotNull
@Email
 private  String email;

@Column(length = 30)
 private  String telefone;

@Column(unique = true, nullable = false)
 private  String login;

@Column(nullable = false)
 private  String senha;

@Column(nullable = false)
 private  String role;

@JsonDeserialize(using = LocalDateTimeDeserializer.class)
@JsonSerialize(using = LocalDateTimeSerializer.class)
@Column(name = "dh_criacao", nullable = false, updatable = false)
 private  LocalDateTime dataCriacao;

@JsonDeserialize(using = LocalDateTimeDeserializer.class)
@JsonSerialize(using = LocalDateTimeSerializer.class)
@Column(name = "dh_alteracao")
 private  LocalDateTime dataAlteracao;


@PrePersist
public void prePersist(){
    setDataCriacao(LocalDateTime.now());
}


}