package ar.com.veterinaria.app.entities.user;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@Entity
@Table(name = "User")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class User implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "idUser", unique = true, nullable = false)
@JsonIgnore
 private  Integer id;

@Column(name = "email", nullable = false, length = 50)
 private  String email;

@Column(name = "password", nullable = false, length = 50)
 private  String password;

@Column(name = "passwordConfirmation", nullable = false, length = 50)
 private  String passwordConfirmation;

@Column(name = "deleted", length = 50)
@JsonIgnore
 private  boolean deleted;

public User() {
    super();
}public User(String email, String password, String passwordConfirmation) {
    super();
    this.email = email;
    this.password = password;
    this.passwordConfirmation = passwordConfirmation;
    this.deleted = false;
}
public void setPassword(String password){
    this.password = password;
}


public String getPassword(){
    return password;
}


public void setPasswordConfirmation(String passwordConfirmation){
    this.passwordConfirmation = passwordConfirmation;
}


public void setEmail(String email){
    this.email = email;
}


public boolean isDeleted(){
    return deleted;
}


public void setId(Integer id){
    this.id = id;
}


public String getEmail(){
    return email;
}


public Integer getId(){
    return id;
}


public String getPasswordConfirmation(){
    return passwordConfirmation;
}


public void setDeleted(boolean deleted){
    this.deleted = deleted;
}


}