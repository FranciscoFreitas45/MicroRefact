package com.jepescados.vendasapi.model.entity;
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
@Entity
@Data
public class Cliente {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Integer id;

@Transient
 private  Usuario usuario;

@Column(length = 15)
 private  String cpf;

@ManyToOne(optional = true)
@JoinColumn(name = "id_endereco")
 private  Endereco endereco;

@JsonDeserialize(using = LocalDateTimeDeserializer.class)
@JsonSerialize(using = LocalDateTimeSerializer.class)
@Column(name = "dh_criacao", nullable = false, updatable = false)
 private  LocalDateTime dataCriacao;

@JsonDeserialize(using = LocalDateTimeDeserializer.class)
@JsonSerialize(using = LocalDateTimeSerializer.class)
@Column(name = "dh_alteracao")
 private  LocalDateTime dataAlteracao;

@Column(name = "id")
 private Integer id;

@Transient
 private UsuarioRequest usuariorequest = new UsuarioRequestImpl();;


@PrePersist
public void prePersist(){
    setDataCriacao(LocalDateTime.now());
}


}