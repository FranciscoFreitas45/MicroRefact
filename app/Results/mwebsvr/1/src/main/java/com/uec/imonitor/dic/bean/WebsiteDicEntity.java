package com.uec.imonitor.dic.bean;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "dic_website")
public class WebsiteDicEntity {

// 指明这个属性映射为数据库的主键
@Id
@GeneratedValue
@Column(name = "website_id", nullable = false)
 private  Integer websiteId;

@Column(name = "name")
 private  String name;

@Column(name = "domain")
 private  String domain;

@Column(name = "url")
 private  String url;

@Column(name = "parent_id")
 private  Integer parentId;

@Column(name = "status")
 private  Integer status;

@Column(name = "description")
 private  String description;

@Column(name = "create_datetime")
 private  Date createDatetime;

@Column(name = "update_datetime")
 private  Date updateDatetime;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public Date getUpdateDatetime(){
    return updateDatetime;
}


public void setDomain(String domain){
    this.domain = domain;
}


public void setDescription(String description){
    this.description = description;
}


public void setCreateDatetime(Date createDatetime){
    this.createDatetime = createDatetime;
}


public Integer getStatus(){
    return status;
}


public String getDescription(){
    return description;
}


public void setStatus(Integer status){
    this.status = status;
}


public void setUrl(String url){
    this.url = url;
}


public String getDomain(){
    return domain;
}


public Integer getWebsiteId(){
    return websiteId;
}


public void setWebsiteId(Integer websiteId){
    this.websiteId = websiteId;
}


public String getUrl(){
    return url;
}


public Date getCreateDatetime(){
    return createDatetime;
}


public void setUpdateDatetime(Date updateDatetime){
    this.updateDatetime = updateDatetime;
}


public void setParentId(Integer parentId){
    this.parentId = parentId;
}


public Integer getParentId(){
    return parentId;
}


}