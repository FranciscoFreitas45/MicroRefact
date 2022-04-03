package com.uec.imonitor.request.bean;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "request_tenant")
public class TenantRequestEntity {

// 指明这个属性映射为数据库的主键
@Id
@GeneratedValue
@Column(name = "request_id", nullable = false)
 private  Integer requestId;

@Column(name = "tenant_id")
 private  Integer tenantId;

@Column(name = "request_name")
 private  String requestName;

@Column(name = "monitot_type")
 private  Integer monitotType;

@Column(name = "status")
 private  Integer status;

@Column(name = "crawl_freq")
 private  Integer crawlFreq;

@Column(name = "create_datetime")
 private  Date createDatetime;

@Column(name = "start_datetime")
 private  Date startDatetime;

@Column(name = "end_datetime")
 private  Date endDatetime;

@Column(name = "comment")
 private  String comment;

@Column(name = "is_deleted")
 private  Integer isDeleted;

@Column(name = "crawl_days")
 private  Integer crawlDays;

@Column(name = "reprint_name")
 private  String reprintName;


public void setCrawlDays(Integer crawlDays){
    this.crawlDays = crawlDays;
}


public Integer getCrawlFreq(){
    return crawlFreq;
}


public String getReprintName(){
    return reprintName;
}


public void setStartDatetime(Date startDatetime){
    this.startDatetime = startDatetime;
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


public void setIsDeleted(Integer isDeleted){
    this.isDeleted = isDeleted;
}


public void setRequestId(Integer requestId){
    this.requestId = requestId;
}


public void setTenantId(Integer tenantId){
    this.tenantId = tenantId;
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


public void setCrawlFreq(Integer crawlFreq){
    this.crawlFreq = crawlFreq;
}


public String getRequestName(){
    return requestName;
}


public Integer getTenantId(){
    return tenantId;
}


public void setCreateDatetime(Date createDatetime){
    this.createDatetime = createDatetime;
}


public void setReprintName(String reprintName){
    this.reprintName = reprintName;
}


public void setStatus(Integer status){
    this.status = status;
}


public void setEndDatetime(Date endDatetime){
    this.endDatetime = endDatetime;
}


public Date getCreateDatetime(){
    return createDatetime;
}


public void setComment(String comment){
    this.comment = comment;
}


public void setRequestName(String requestName){
    this.requestName = requestName;
}


}