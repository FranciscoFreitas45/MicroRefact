package io.swagger.model;
 import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.validation.annotation.Validated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints;
import io.swagger.Request.AddressRequest;
import io.swagger.Request.Impl.AddressRequestImpl;
import io.swagger.DTO.Address;
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-20T13:31:05.475Z")
@Entity
@Table(name = "medic")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt", "password" }, allowGetters = false, allowSetters = true)
public class Medic {

@JsonProperty("id")
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@JsonProperty("username")
@Column(unique = true)
 private  String username;

@JsonProperty("firstName")
@Column
 private  String firstName;

@JsonProperty("lastName")
@Column
 private  String lastName;

@JsonProperty("email")
@Column(nullable = false)
 private  String email;

@JsonProperty("password")
@Column
 private  String password;

@JsonProperty("phone")
@Column(unique = true)
 private  String phone;

@JsonProperty("userStatus")
@Column
 private  Integer userStatus;

@Transient
 private  List<Address> address;

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
 private AddressRequest addressrequest = new AddressRequestImpl();;


public String getPhone(){
    return phone;
}


public void setPassword(String password){
    this.password = password;
}


public void setAddress(List<Address> address){
 addressrequest.setAddress(address,this.id);
}



public void setUsername(String username){
    this.username = username;
}


public LocalDateTime getUpdatedAt(){
    return updatedAt;
}


public Long getId(){
    return id;
}


public void setPhone(String phone){
    this.phone = phone;
}


public Integer getUserStatus(){
    return userStatus;
}


public String getLastName(){
    return lastName;
}


public String getUsername(){
    return username;
}


public void setLastName(String lastName){
    this.lastName = lastName;
}


public Timestamp getCreatedAt(){
    return createdAt;
}


public void setCreatedAt(Timestamp createdAt){
    this.createdAt = createdAt;
}


public String getPassword(){
    return password;
}


public void setEmail(String email){
    this.email = email;
}


public void setFirstName(String firstName){
    this.firstName = firstName;
}


public void setId(Long id){
    this.id = id;
}


public String getEmail(){
    return email;
}


public List<Address> getAddress(){
  this.address = addressrequest.getAddress(this.id);
return this.address;
}


public void setUserStatus(Integer userStatus){
    this.userStatus = userStatus;
}


public String getFirstName(){
    return firstName;
}


public void setUpdatedAt(LocalDateTime updatedAt){
    this.updatedAt = updatedAt;
}


}