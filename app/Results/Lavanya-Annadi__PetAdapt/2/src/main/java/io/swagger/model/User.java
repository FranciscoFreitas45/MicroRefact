package io.swagger.model;
 import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.Request.AddressRequest;
import io.swagger.Request.Impl.AddressRequestImpl;
import io.swagger.DTO.Address;
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-20T13:31:05.475Z")
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt", "password" }, allowGetters = false, allowSetters = true)
public class User {

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

@JsonIgnore
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


@ApiModelProperty(value = "")
public String getPhone(){
    return phone;
}


public User lastName(String lastName){
    this.lastName = lastName;
    return this;
}


public void setPassword(String password){
    this.password = password;
}


public User userStatus(Integer userStatus){
    this.userStatus = userStatus;
    return this;
}


@ApiModelProperty(value = "")
public Long getId(){
    return id;
}


@ApiModelProperty(value = "User Status")
public Integer getUserStatus(){
    return userStatus;
}


@ApiModelProperty(value = "")
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


public User password(String password){
    this.password = password;
    return this;
}


public void setId(Long id){
    this.id = id;
}


public List<Address> getAddress(){
  this.address = addressrequest.getAddress(this.id);
return this.address;
}


public User id(Long id){
    this.id = id;
    return this;
}


public void setUserStatus(Integer userStatus){
    this.userStatus = userStatus;
}


@ApiModelProperty(hidden = true)
public void setUpdatedAt(LocalDateTime updatedAt){
    this.updatedAt = updatedAt;
}


public User email(String email){
    this.email = email;
    return this;
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


public void setPhone(String phone){
    this.phone = phone;
}


@ApiModelProperty(value = "")
public String getLastName(){
    return lastName;
}


public User firstName(String firstName){
    this.firstName = firstName;
    return this;
}


@ApiModelProperty(value = "")
public String getPassword(){
    return password;
}


public void setEmail(String email){
    this.email = email;
}


public void setFirstName(String firstName){
    this.firstName = firstName;
}


public User phone(String phone){
    this.phone = phone;
    return this;
}


@ApiModelProperty(value = "")
public String getEmail(){
    return email;
}


@Override
public String toString(){
    return "User [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password + ", phone=" + phone + ", userStatus=" + userStatus + ", address=" + address + ", updatedAt=" + updatedAt + ", createdAt=" + createdAt + "]";
}


@ApiModelProperty(value = "")
public String getFirstName(){
    return firstName;
}


public User username(String username){
    this.username = username;
    return this;
}


}