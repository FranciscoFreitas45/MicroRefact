package com.uec.imonitor.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
public class WebsiteDicEntity {

 private  Integer websiteId;

 private  String name;

 private  String domain;

 private  String url;

 private  Integer parentId;

 private  Integer status;

 private  String description;

 private  Date createDatetime;

 private  Date updateDatetime;


public String getName(){
    return name;
}


public Date getUpdateDatetime(){
    return updateDatetime;
}


public Integer getStatus(){
    return status;
}


public String getDescription(){
    return description;
}


public String getDomain(){
    return domain;
}


public Integer getWebsiteId(){
    return websiteId;
}


public String getUrl(){
    return url;
}


public Date getCreateDatetime(){
    return createDatetime;
}


public Integer getParentId(){
    return parentId;
}


}