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
public class Company {

 private  Integer companyId;

 private  String companyName;

 private  String contacts;

 private  String mobile;

 private  String address;

 private  Integer version;

 private  Date createTime;

 private  Date updateTime;

 private  String status;

 private  String value;

 private  String name;

public Company() {
}public Company(Integer companyId, String companyName, String contacts, String mobile, String address, Integer version, Date createTime, Date updateTime, String status) {
    this.companyId = companyId;
    this.companyName = companyName;
    this.contacts = contacts;
    this.mobile = mobile;
    this.address = address;
    this.version = version;
    this.createTime = createTime;
    this.updateTime = updateTime;
    this.status = status;
}
public Integer getVersion(){
    return version;
}


public Date getCreateTime(){
    return createTime;
}


public String getName(){
    return name;
}


public void setVersion(Integer version){
    this.version = version;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public String getStatus(){
    return status;
}


public String getContacts(){
    return contacts;
}


public Date getUpdateTime(){
    return updateTime;
}


public String getValue(){
    return value;
}


public Integer getCompanyId(){
    return companyId;
}


public String getCompanyName(){
    return companyName;
}


public String getMobile(){
    return mobile;
}


public String getAddress(){
    return address;
}


public void setCompanyId(Integer companyId){
    this.companyId = companyId;
}


}