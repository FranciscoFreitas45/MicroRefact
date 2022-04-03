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
public class StorageIoBatch {

 private  Integer batchId;

 private  Integer repoId;

 private  String memo;

 private  Integer operator;

 private  String bizType;

 private  Integer amount;

 private  String status;

 private  Date gmtCreate;

 private  Date gmtModify;

 private  Integer version;

 private  String value;

 private  String display;

 private  String value;

 private  String display;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


public Integer getRepoId(){
    return repoId;
}


public Integer getVersion(){
    return this.version;
}


public Date getGmtCreate(){
    return this.gmtCreate;
}


public Date getGmtModify(){
    return this.gmtModify;
}


public BizType getBizType(String value){
    for (BizType bizType : BizType.values()) {
        if (bizType.getValue().equals(value)) {
            return bizType;
        }
    }
    return null;
}


public Status getStatus(String value){
    for (Status status : Status.values()) {
        if (status.getValue().equals(value)) {
            return status;
        }
    }
    return null;
}


public String getValue(){
    return value;
}


public String getMemo(){
    return this.memo;
}


public Integer getBatchId(){
    return batchId;
}


public Integer getOperator(){
    return operator;
}


public String getDisplay(){
    return display;
}


public Integer getAmount(){
    return this.amount;
}


public void setAmount(Integer amount){
    this.amount = amount;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ batchId).concat("/setAmount"))

.queryParam("amount",amount)
;
restTemplate.put(builder.toUriString(),null);
}


}