package com.uec.imonitor.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
public class RequestSiteEntity {

 private  Integer innerid;

 private  Integer requestId;

 private  Integer siteId;

 private  Integer status;

 private  Date createDatetime;

 private  Date updateDatetime;

 private  Integer isDeleted;

 private  Integer isWhiteList;


public Date getUpdateDatetime(){
    return updateDatetime;
}


public Integer getSiteId(){
    return siteId;
}


public Integer getStatus(){
    return status;
}


public void setIsWhiteList(Integer isWhiteList){
    this.isWhiteList = isWhiteList;
}


public void setInnerid(Integer innerid){
    this.innerid = innerid;
}


public Integer getIsDeleted(){
    return isDeleted;
}


public Date getCreateDatetime(){
    return createDatetime;
}


public Integer getInnerid(){
    return innerid;
}


public Integer getIsWhiteList(){
    return isWhiteList;
}


public Integer getRequestId(){
    return requestId;
}


}