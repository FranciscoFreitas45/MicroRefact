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
public class InwarehouseDetail {

 private  Integer id;

 private  Integer inwarehouseId;

 private  String bizType;

 private  String positionLabel;

 private  Integer bookId;

 private  Integer amount;

 private  Date gmtCreate;

 private  Date gmtModify;

 private  Integer version;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";

// Constructors
/**
 * default constructor
 */
public InwarehouseDetail() {
}
public Integer getVersion(){
    return this.version;
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


public String getBizType(){
    return bizType;
}


public String getPositionLabel(){
    return this.positionLabel;
}


public Integer getBookId(){
    return this.bookId;
}


public Integer getInwarehouseId(){
    return this.inwarehouseId;
}


public Integer getAmount(){
    return this.amount;
}


public void setAmount(Integer amount){
    this.amount = amount;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setAmount"))

.queryParam("amount",amount)
;
restTemplate.put(builder.toUriString(),null);
}


}