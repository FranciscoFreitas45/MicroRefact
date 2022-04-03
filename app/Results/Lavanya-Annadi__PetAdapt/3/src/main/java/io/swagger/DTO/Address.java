package io.swagger.DTO;
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
public class Address {

 private  Long id;

 private  String type;

 private  String permanent;

 private  String street;

 private  String city;

 private  String pin_code;

 private  String country;

 private  String type_of_user;

 private  LocalDateTime updatedAt;

 private  Timestamp createdAt;


public String getCountry(){
    return country;
}


public LocalDateTime getUpdatedAt(){
    return updatedAt;
}


public String getPin_code(){
    return pin_code;
}


public Long getId(){
    return id;
}


public String getType_of_user(){
    return type_of_user;
}


public Timestamp getCreatedAt(){
    return createdAt;
}


public String getType(){
    return type;
}


public String getStreet(){
    return street;
}


public String getPermanent(){
    return permanent;
}


public String getCity(){
    return city;
}


}