package com.zis.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
public class InwarehousePosition {

 private  Integer id;

 private  Integer inwarehouseId;

 private  String positionLabel;

 private  Integer capacity;

 private  Integer currentAmount;

 private  boolean isFull;

 private  Date gmtCreate;

 private  Date gmtModify;

 private  Integer version;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";

// Constructors
/**
 * default constructor
 */
public InwarehousePosition() {
}
public Integer getVersion(){
    return this.version;
}


public Integer getCurrentAmount(){
    return this.currentAmount;
}


public void setVersion(Integer version){
    this.version = version;
}


public Date getGmtCreate(){
    return this.gmtCreate;
}


public Integer getId(){
    return this.id;
}


public Date getGmtModify(){
    return this.gmtModify;
}


public String getPositionLabel(){
    return this.positionLabel;
}


public void setIsFull(boolean isFull){
    this.isFull = isFull;
}


public boolean getIsFull(){
    return this.isFull;
}


public void setCapacity(Integer capacity){
    this.capacity = capacity;
}


public Integer getInwarehouseId(){
    return this.inwarehouseId;
}


public Integer getCapacity(){
    return this.capacity;
}


public void setInwarehouseId(Integer inwarehouseId){
    this.inwarehouseId = inwarehouseId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setInwarehouseId"))

.queryParam("inwarehouseId",inwarehouseId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPositionLabel(String positionLabel){
    this.positionLabel = positionLabel;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setPositionLabel"))

.queryParam("positionLabel",positionLabel)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCurrentAmount(Integer currentAmount){
    this.currentAmount = currentAmount;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setCurrentAmount"))

.queryParam("currentAmount",currentAmount)
;
restTemplate.put(builder.toUriString(),null);
}


public void setGmtCreate(Date gmtCreate){
    this.gmtCreate = gmtCreate;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setGmtCreate"))

.queryParam("gmtCreate",gmtCreate)
;
restTemplate.put(builder.toUriString(),null);
}


public void setGmtModify(Date gmtModify){
    this.gmtModify = gmtModify;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setGmtModify"))

.queryParam("gmtModify",gmtModify)
;
restTemplate.put(builder.toUriString(),null);
}


}