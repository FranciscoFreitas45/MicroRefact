package io.swagger.model;
 import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.validation.annotation.Validated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints;
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-20T13:31:05.475Z")
@Entity
@Table(name = "address")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = false, allowSetters = false)
public class Address {

@JsonProperty("id")
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@JsonProperty("type")
 private  String type;

@JsonProperty("permanent")
@Column
 private  String permanent;

@JsonProperty("street")
@Column
 private  String street;

@JsonProperty("city")
 private  String city;

@JsonProperty("pin_code")
@Column
 private  String pin_code;

@JsonProperty("country")
@Column
 private  String country;

@JsonProperty("type_of_user")
@Column
 private  String type_of_user;

@JsonProperty("updatedAt")
@Column
@UpdateTimestamp
 private  LocalDateTime updatedAt;

@JsonProperty("createdAt")
@Column(updatable = false)
@CreationTimestamp
 private  Timestamp createdAt;


public void setCountry(String country){
    this.country = country;
}


public void setStreet(String street){
    this.street = street;
}


public String getCountry(){
    return country;
}


public void setCity(String city){
    this.city = city;
}


public LocalDateTime getUpdatedAt(){
    return updatedAt;
}


public String getPin_code(){
    return pin_code;
}


public void setType_of_user(String type_of_user){
    this.type_of_user = type_of_user;
}


public Long getId(){
    return id;
}


public String getType_of_user(){
    return type_of_user;
}


public void setType(String type){
    this.type = type;
}


public Timestamp getCreatedAt(){
    return createdAt;
}


@ApiModelProperty(hidden = true)
public void setCreatedAt(Timestamp createdAt){
    this.createdAt = createdAt;
}


public String getType(){
    return type;
}


public void setPermanent(String permanent){
    this.permanent = permanent;
}


public String getStreet(){
    return street;
}


public void setId(Long id){
    this.id = id;
}


public void setPin_code(String pin_code){
    this.pin_code = pin_code;
}


public String getPermanent(){
    return permanent;
}


@ApiModelProperty(hidden = true)
public void setUpdatedAt(LocalDateTime updatedAt){
    this.updatedAt = updatedAt;
}


public String getCity(){
    return city;
}


}