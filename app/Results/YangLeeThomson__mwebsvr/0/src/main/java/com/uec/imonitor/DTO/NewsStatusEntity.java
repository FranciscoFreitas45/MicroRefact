package com.uec.imonitor.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
public class NewsStatusEntity {

 private  Integer innerid;

 private  String tableName;

 private  Integer recordId;

 private  String webpageCode;

 private  Integer status;

 private  Date lastModifyTime;


public void setTableName(String tableName){
    this.tableName = tableName;
}


public Date getLastModifyTime(){
    return lastModifyTime;
}


public Integer getRecordId(){
    return recordId;
}


public Integer getInnerid(){
    return innerid;
}


public String getTableName(){
    return tableName;
}


public Integer getStatus(){
    return status;
}


public String getWebpageCode(){
    return webpageCode;
}


}