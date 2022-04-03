package io.swagger.DTO;
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
public class Order {

 private  Long id;

 private  Integer quantity;

 private  Date shipDate;

 private  Integer complete;

 private  String status;

 private  Provider provider;

 private  LocalDateTime updatedAt;

 private  Timestamp createdAt;

 private  User user;

 private  Pet pet;


public Date getShipDate(){
    return shipDate;
}


public Pet getPet(){
    return pet;
}


public Integer getQuantity(){
    return quantity;
}


public LocalDateTime getUpdatedAt(){
    return updatedAt;
}


public User getUser(){
    return user;
}


public Long getId(){
    return id;
}


public String getStatus(){
    return status;
}


public Integer getComplete(){
    return complete;
}


public Timestamp getCreatedAt(){
    return createdAt;
}


public void setShipDate(Date shipDate){
    this.shipDate = shipDate;
}


public void setId(Long id){
    this.id = id;
}


public Provider getProvider(){
    return provider;
}


public void setUpdatedAt(LocalDateTime updatedAt){
    this.updatedAt = updatedAt;
}


}