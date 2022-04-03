package com.poseidon.make.domain;
 import java.time.OffsetDateTime;
import java.util.StringJoiner;
public class MakeVO {

 private  Long id;

 private  String makeName;

 private  String description;

 private  OffsetDateTime createdOn;

 private  OffsetDateTime modifiedOn;

 private  String createdBy;

 private  String modifiedBy;


public void setMakeName(String makeName){
    this.makeName = makeName;
}


public OffsetDateTime getModifiedOn(){
    return modifiedOn;
}


public OffsetDateTime getCreatedOn(){
    return createdOn;
}


public Long getId(){
    return id;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public String getModifiedBy(){
    return modifiedBy;
}


public String getMakeName(){
    return makeName;
}


public void setCreatedOn(OffsetDateTime createdOn){
    this.createdOn = createdOn;
}


public void setModifiedOn(OffsetDateTime modifiedOn){
    this.modifiedOn = modifiedOn;
}


public void setCreatedBy(String createdBy){
    this.createdBy = createdBy;
}


public void setId(Long id){
    this.id = id;
}


@Override
public String toString(){
    return new StringJoiner(", ", MakeVO.class.getSimpleName() + "[", "]").add("id=" + id).add("makeName='" + makeName + "'").add("description='" + description + "'").add("createdOn=" + createdOn).add("modifiedOn=" + modifiedOn).add("createdBy='" + createdBy + "'").add("modifiedBy='" + modifiedBy + "'").toString();
}


public void setModifiedBy(String modifiedBy){
    this.modifiedBy = modifiedBy;
}


public String getCreatedBy(){
    return createdBy;
}


}