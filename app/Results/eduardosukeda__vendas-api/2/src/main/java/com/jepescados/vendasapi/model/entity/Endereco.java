package com.jepescados.vendasapi.model.entity;
 import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
public class Endereco {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Integer id;

@Column(nullable = false, length = 150)
 private  String rua;

@Column
 private  Integer numero;

@Column(nullable = false, length = 15)
 private  String cep;

@Column(nullable = false, length = 150)
 private  String cidade;

@Column(nullable = false, length = 150)
 private  String estado;

@Column(nullable = false, length = 150)
 private  String bairro;

@Column(length = 150)
 private  String complemento;

@Column(length = 150)
 private  String pontoReferencia;

@Column(length = 150)
 private  String telefoneEndereco;

@Column(length = 150)
 private  String observacao;

@Column(length = 150)
 private  String nomeRecebedor;

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