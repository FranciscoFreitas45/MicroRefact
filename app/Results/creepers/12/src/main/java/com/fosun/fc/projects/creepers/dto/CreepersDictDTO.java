package com.fosun.fc.projects.creepers.dto;
 import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
public class CreepersDictDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String category;

 private  String createdBy;

@Temporal(TemporalType.DATE)
 private  Date createdDt;

@Temporal(TemporalType.DATE)
 private  Date endDt;

 private  String key1;

 private  String key2;

@Temporal(TemporalType.DATE)
 private  Date startDt;

 private  String updatedBy;

@Temporal(TemporalType.DATE)
 private  Date updatedDt;

 private  String value1;

 private  String value2;

public CreepersDictDTO() {
}
public void setUpdatedBy(String updatedBy){
    this.updatedBy = updatedBy;
}


public String getUpdatedBy(){
    return this.updatedBy;
}


public Date getStartDt(){
    return this.startDt;
}


public void setEndDt(Date endDt){
    this.endDt = endDt;
}


public String getCategory(){
    return this.category;
}


public String getKey2(){
    return this.key2;
}


public String getKey1(){
    return this.key1;
}


public Date getUpdatedDt(){
    return this.updatedDt;
}


public String getValue1(){
    return this.value1;
}


public String getValue2(){
    return this.value2;
}


public void setValue2(String value2){
    this.value2 = value2;
}


public void setValue1(String value1){
    this.value1 = value1;
}


public void setCategory(String category){
    this.category = category;
}


public Date getCreatedDt(){
    return this.createdDt;
}


public Date getEndDt(){
    return this.endDt;
}


public void setCreatedBy(String createdBy){
    this.createdBy = createdBy;
}


public void setKey1(String key1){
    this.key1 = key1;
}


public void setKey2(String key2){
    this.key2 = key2;
}


public void setStartDt(Date startDt){
    this.startDt = startDt;
}


public void setUpdatedDt(Date updatedDt){
    this.updatedDt = updatedDt;
}


public void setCreatedDt(Date createdDt){
    this.createdDt = createdDt;
}


public String getCreatedBy(){
    return this.createdBy;
}


}