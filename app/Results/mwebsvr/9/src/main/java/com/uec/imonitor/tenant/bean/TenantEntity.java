package com.uec.imonitor.tenant.bean;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "tenant")
public class TenantEntity {

// 指明这个属性映射为数据库的主键
@Id
@GeneratedValue
@Column(name = "tenant_id", nullable = false)
 private  Integer tenantId;

@Column(name = "tenant_name")
 private  String tenantName;

@Column(name = "company")
 private  String company;

@Column(name = "crawl_freq")
 private  Integer crawlFreq;

@Column(name = "create_datetime")
 private  Date createDatetime;

@Column(name = "crawl_days")
 private  Integer crawlDays;

@Column(name = "reprint_name")
 private  String reprintName;

@Column(name = "tenant_mark")
 private  String tenantMark;


public Integer getCrawlDays(){
    return crawlDays;
}


public void setCrawlDays(Integer crawlDays){
    this.crawlDays = crawlDays;
}


public Integer getCrawlFreq(){
    return crawlFreq;
}


public void setCrawlFreq(Integer crawlFreq){
    this.crawlFreq = crawlFreq;
}


public String getReprintName(){
    return reprintName;
}


public String getTenantName(){
    return tenantName;
}


public Integer getTenantId(){
    return tenantId;
}


public void setCreateDatetime(Date createDatetime){
    this.createDatetime = createDatetime;
}


public String getTenantMark(){
    return tenantMark;
}


public void setReprintName(String reprintName){
    this.reprintName = reprintName;
}


public void setTenantName(String tenantName){
    this.tenantName = tenantName;
}


public String getCompany(){
    return company;
}


public Date getCreateDatetime(){
    return createDatetime;
}


public void setTenantId(Integer tenantId){
    this.tenantId = tenantId;
}


public void setCompany(String company){
    this.company = company;
}


public void setTenantMark(String tenantMark){
    this.tenantMark = tenantMark;
}


}