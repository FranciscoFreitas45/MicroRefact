package io.swagger.DTO;
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
public class Session {

 private  Long id;

 private  String sessionId;

 private  String ipAddress;

 private  Boolean status;

 private  User user;

 private  Medic medic;

 private  Provider provider;

 private  String type;

 private  LocalDateTime updatedAt;

 private  Timestamp createdAt;

 private Long id;

 private Long id;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


public String getIpAddress(){
    return ipAddress;
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


public Boolean getStatus(){
    return status;
}


public Timestamp getCreatedAt(){
    return createdAt;
}


public String getType(){
    return type;
}


public Provider getProvider(){
    return provider;
}


public String getSessionId(){
    return sessionId;
}


public Medic getMedic(){
    return medic;
}


public void setStatus(Boolean status){
    this.status = status;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setStatus"))

.queryParam("status",status)
;
restTemplate.put(builder.toUriString(),null);
}


public void setMedic(Medic medic){
 medicrequest.setMedic(medic,this.id);
 


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setMedic"))

.queryParam("medic",medic)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSessionId(String sessionId){
    this.sessionId = sessionId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setSessionId"))

.queryParam("sessionId",sessionId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setIpAddress(String ipAddress){
    this.ipAddress = ipAddress;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setIpAddress"))

.queryParam("ipAddress",ipAddress)
;
restTemplate.put(builder.toUriString(),null);
}


}