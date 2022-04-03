package com.poseidon.init.entity;
 import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
@MappedSuperclass
public class CommonEntity {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  Long id;

@CreatedDate
@Column(name = "createdOn")
 private  LocalDateTime createdOn;

@LastModifiedDate
@Column(name = "modifiedOn")
 private  LocalDateTime modifiedOn;

@Column(name = "createdBy")
 private  String createdBy;

@Column(name = "modifiedBy")
 private  String modifiedBy;


public LocalDateTime getModifiedOn(){
    return modifiedOn;
}


public void setModifiedOn(LocalDateTime modifiedOn){
    this.modifiedOn = modifiedOn;
}


public LocalDateTime getCreatedOn(){
    return createdOn;
}


public void setCreatedBy(String createdBy){
    this.createdBy = createdBy;
}


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


public String getModifiedBy(){
    return modifiedBy;
}


public void setModifiedBy(String modifiedBy){
    this.modifiedBy = modifiedBy;
}


public void setCreatedOn(LocalDateTime createdOn){
    this.createdOn = createdOn;
}


public String getCreatedBy(){
    return createdBy;
}


}