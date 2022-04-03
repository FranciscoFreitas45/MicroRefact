package io.swagger.DTO;
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
public class User {

 private  Long id;

 private  String username;

 private  String firstName;

 private  String lastName;

 private  String email;

 private  String password;

 private  String phone;

 private  Integer userStatus;

 private  List<Address> address;

 private  LocalDateTime updatedAt;

 private  Timestamp createdAt;


@ApiModelProperty(value = "")
public String getPhone(){
    return phone;
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


public Timestamp getCreatedAt(){
    return createdAt;
}


public List<Address> getAddress(){
    return address;
}


public LocalDateTime getUpdatedAt(){
    return updatedAt;
}


@ApiModelProperty(value = "")
public String getLastName(){
    return lastName;
}


@ApiModelProperty(value = "")
public String getPassword(){
    return password;
}


@ApiModelProperty(value = "")
public String getEmail(){
    return email;
}


@ApiModelProperty(value = "")
public String getFirstName(){
    return firstName;
}


}