package com.jepescados.vendasapi.DTO;
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
public class Endereco {

 private  Integer id;

 private  String rua;

 private  Integer numero;

 private  String cep;

 private  String cidade;

 private  String estado;

 private  String bairro;

 private  String complemento;

 private  String pontoReferencia;

 private  String telefoneEndereco;

 private  String observacao;

 private  String nomeRecebedor;

 private  LocalDateTime dataCriacao;

 private  LocalDateTime dataAlteracao;


}