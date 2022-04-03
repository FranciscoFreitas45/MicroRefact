package com.poseidon.init.domain;
 import java.time.LocalDateTime;
public class CommonVO {

 private  Long id;

 private  LocalDateTime createdDate;

 private  LocalDateTime modifiedDate;

 private  String createdBy;

 private  String modifiedBy;


public void setModifiedDate(LocalDateTime modifiedDate){
    this.modifiedDate = modifiedDate;
}


public void setCreatedDate(LocalDateTime createdDate){
    this.createdDate = createdDate;
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


public LocalDateTime getCreatedDate(){
    return createdDate;
}


public String getModifiedBy(){
    return modifiedBy;
}


public void setModifiedBy(String modifiedBy){
    this.modifiedBy = modifiedBy;
}


public String getCreatedBy(){
    return createdBy;
}


public LocalDateTime getModifiedDate(){
    return modifiedDate;
}


}