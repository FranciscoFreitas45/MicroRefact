package io.swagger.model;
 import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.Request.UserRequest;
import io.swagger.Request.Impl.UserRequestImpl;
import io.swagger.DTO.User;
import io.swagger.Request.PetRequest;
import io.swagger.Request.Impl.PetRequestImpl;
import io.swagger.DTO.Pet;
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-20T13:31:05.475Z")
@Entity
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class Order {

@JsonProperty("id")
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@JsonProperty("quantity")
@Column
 private  Integer quantity;

@JsonProperty("shipDate")
@Column
@DateTimeFormat
 private  Date shipDate;

@JsonProperty("complete")
@Column
 private  Integer complete;

@JsonProperty("status")
@Column
 private  String status;

@JsonProperty("provider")
@OneToOne
 private  Provider provider;

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

@Transient
 private  User user;

@Transient
 private  Pet pet;

@Column(name = "id")
 private Long id;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

@Column(name = "id")
 private Long id;

@Transient
 private PetRequest petrequest = new PetRequestImpl();;


public Date getShipDate(){
    return shipDate;
}


public Pet getPet(){
  this.pet = petrequest.getPet(this.id);
return this.pet;
}


public Integer getQuantity(){
    return quantity;
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


public void setComplete(Integer complete){
    this.complete = complete;
}


public String getStatus(){
    return status;
}


public void setStatus(String status){
    this.status = status;
}


public Integer getComplete(){
    return complete;
}


public Timestamp getCreatedAt(){
    return createdAt;
}


public void setCreatedAt(Timestamp createdAt){
    this.createdAt = createdAt;
}


public void setShipDate(Date shipDate){
    this.shipDate = shipDate;
}


public void setQuantity(Integer quantity){
    this.quantity = quantity;
}


public void setId(Long id){
    this.id = id;
}


public Provider getProvider(){
    return provider;
}


public void setUser(User user){
 userrequest.setUser(user,this.id);
}



public void setUpdatedAt(LocalDateTime updatedAt){
    this.updatedAt = updatedAt;
}


public void setPet(Pet pet){
 petrequest.setPet(pet,this.id);
}



}