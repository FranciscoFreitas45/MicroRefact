package com.uec.imonitor.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
public class TenantRequestEntity {

 private  Integer requestId;

 private  Integer tenantId;

 private  String requestName;

 private  Integer monitotType;

 private  Integer status;

 private  Integer crawlFreq;

 private  Date createDatetime;

 private  Date startDatetime;

 private  Date endDatetime;

 private  String comment;

 private  Integer isDeleted;

 private  Integer crawlDays;

 private  String reprintName;


public Integer getCrawlFreq(){
    return crawlFreq;
}


public String getReprintName(){
    return reprintName;
}


public void setMonitotType(Integer monitotType){
    this.monitotType = monitotType;
}


public Integer getStatus(){
    return status;
}


public Integer getIsDeleted(){
    return isDeleted;
}


public void setRequestId(Integer requestId){
    this.requestId = requestId;
}


public Integer getMonitotType(){
    return monitotType;
}


public Date getStartDatetime(){
    return startDatetime;
}


public String getComment(){
    return comment;
}


public Integer getRequestId(){
    return requestId;
}


public Date getEndDatetime(){
    return endDatetime;
}


public Integer getCrawlDays(){
    return crawlDays;
}


public String getRequestName(){
    return requestName;
}


public Integer getTenantId(){
    return tenantId;
}


public void setReprintName(String reprintName){
    this.reprintName = reprintName;
}


public void setEndDatetime(Date endDatetime){
    this.endDatetime = endDatetime;
}


public Date getCreateDatetime(){
    return createDatetime;
}


public void setRequestName(String requestName){
    this.requestName = requestName;
}


}