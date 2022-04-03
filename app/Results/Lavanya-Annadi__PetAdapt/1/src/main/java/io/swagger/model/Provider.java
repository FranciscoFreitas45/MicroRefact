package io.swagger.model;
 import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-20T13:31:05.475Z")
@Entity
@Table(name = "provider")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt", "password" }, allowGetters = false, allowSetters = true)
public class Provider {

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

@JsonProperty("address")
@OneToMany(cascade = CascadeType.ALL)
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

@JsonProperty("status")
@Column
 private  Integer status;


public String getPhone(){
    return phone;
}


public void setPassword(String password){
    this.password = password;
}


public void setAddress(List<Address> address){
    this.address = address;
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


public Integer getStatus(){
    return status;
}


public String getLastName(){
    return lastName;
}


public void setStatus(Integer status){
    this.status = status;
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
    return address;
}


public String getFirstName(){
    return firstName;
}


public void setUpdatedAt(LocalDateTime updatedAt){
    this.updatedAt = updatedAt;
}


}