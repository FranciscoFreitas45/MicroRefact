package com.uec.imonitor.es.bean;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "news_status")
public class NewsStatusEntity {

// 指明这个属性映射为数据库的主键
@Id
@GeneratedValue
@Column(name = "innerid", nullable = false)
 private  Integer innerid;

@Column(name = "table_name")
 private  String tableName;

@Column(name = "record_id")
 private  Integer recordId;

@Column(name = "webpage_code")
 private  String webpageCode;

@Column(name = "status")
 private  Integer status;

@Column(name = "last_modify_time")
 private  Date lastModifyTime;


public void setInnerid(Integer innerid){
    this.innerid = innerid;
}


public void setTableName(String tableName){
    this.tableName = tableName;
}


public void setRecordId(Integer recordId){
    this.recordId = recordId;
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


public void setLastModifyTime(Date lastModifyTime){
    this.lastModifyTime = lastModifyTime;
}


public String getTableName(){
    return tableName;
}


public void setWebpageCode(String webpageCode){
    this.webpageCode = webpageCode;
}


public Integer getStatus(){
    return status;
}


public String getWebpageCode(){
    return webpageCode;
}


public void setStatus(Integer status){
    this.status = status;
}


}