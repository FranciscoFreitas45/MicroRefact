package com.uec.imonitor.request.bean;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "request_tenant_site")
public class RequestSiteEntity {

// 指明这个属性映射为数据库的主键
@Id
@GeneratedValue
@Column(name = "innerid", nullable = false)
 private  Integer innerid;

@Column(name = "request_id")
 private  Integer requestId;

@Column(name = "site_id")
 private  Integer siteId;

@Column(name = "status")
 private  Integer status;

@Column(name = "create_datetime")
 private  Date createDatetime;

@Column(name = "update_datetime")
 private  Date updateDatetime;

@Column(name = "is_deleted")
 private  Integer isDeleted;

@Column(name = "is_white_list")
 private  Integer isWhiteList;


public void setSiteId(Integer siteId){
    this.siteId = siteId;
}


public Date getUpdateDatetime(){
    return updateDatetime;
}


public Integer getSiteId(){
    return siteId;
}


public Integer getStatus(){
    return status;
}


public void setCreateDatetime(Date createDatetime){
    this.createDatetime = createDatetime;
}


public void setIsWhiteList(Integer isWhiteList){
    this.isWhiteList = isWhiteList;
}


public void setStatus(Integer status){
    this.status = status;
}


public void setInnerid(Integer innerid){
    this.innerid = innerid;
}


public Integer getIsDeleted(){
    return isDeleted;
}


public void setIsDeleted(Integer isDeleted){
    this.isDeleted = isDeleted;
}


public Date getCreateDatetime(){
    return createDatetime;
}


public void setUpdateDatetime(Date updateDatetime){
    this.updateDatetime = updateDatetime;
}


public Integer getInnerid(){
    return innerid;
}


public void setRequestId(Integer requestId){
    this.requestId = requestId;
}


public Integer getIsWhiteList(){
    return isWhiteList;
}


public Integer getRequestId(){
    return requestId;
}


}