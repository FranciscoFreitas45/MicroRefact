package io.swagger.model;
 import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints;
import io.swagger.Request.UserRequest;
import io.swagger.Request.Impl.UserRequestImpl;
import io.swagger.DTO.User;
import io.swagger.Request.MedicRequest;
import io.swagger.Request.Impl.MedicRequestImpl;
import io.swagger.DTO.Medic;
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-20T13:31:05.475Z")
@Entity
@Table(name = "session", uniqueConstraints = @UniqueConstraint(columnNames = { "sessionId", "ipAddress" }))
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class Session {

@JsonProperty("id")
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@JsonProperty("session")
@Column(unique = true)
 private  String sessionId;

@JsonProperty("ipAddress")
@Column
 private  String ipAddress;

@JsonProperty("status")
@Column
 private  Boolean status;

@Transient
 private  User user;

@Transient
 private  Medic medic;

@JsonProperty("provider")
@OneToOne
 private  Provider provider;

@JsonProperty("user_type")
@Column
 private  String type;

@JsonProperty("updatedAt")
@Column
@UpdateTimestamp
@ApiModelProperty(hidden = true)
 private  LocalDateTime updatedAt;

@JsonProperty("createdAt")
@Column(updatable = false)
@CreationTimestamp
@ApiModelProperty(hidden = true)
 private  Timestamp createdAt;

@Column(name = "id")
 private Long id;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

@Column(name = "id")
 private Long id;

@Transient
 private MedicRequest medicrequest = new MedicRequestImpl();;


public String getIpAddress(){
    return ipAddress;
}


public void setProvider(Provider provider){
    this.provider = provider;
}


public LocalDateTime getUpdatedAt(){
    return updatedAt;
}


public User getUser(){
  this.user = userrequest.getUser(this.id);
return this.user;
}


public Long getId(){
    return id;
}


public void setType(String type){
    this.type = type;
}


public Boolean getStatus(){
    return status;
}


public void setIpAddress(String ipAddress){
    this.ipAddress = ipAddress;
}


public void setStatus(Boolean status){
    this.status = status;
}


public Timestamp getCreatedAt(){
    return createdAt;
}


public void setCreatedAt(Timestamp createdAt){
    this.createdAt = createdAt;
}


public void setSessionId(String sessionId){
    this.sessionId = sessionId;
}


public String getType(){
    return type;
}


public void setId(Long id){
    this.id = id;
}


public Provider getProvider(){
    return provider;
}


public String getSessionId(){
    return sessionId;
}


public void setUser(User user){
 userrequest.setUser(user,this.id);
}



public Medic getMedic(){
  this.medic = medicrequest.getMedic(this.id);
return this.medic;
}


public void setUpdatedAt(LocalDateTime updatedAt){
    this.updatedAt = updatedAt;
}


public void setMedic(Medic medic){
 medicrequest.setMedic(medic,this.id);
}



}