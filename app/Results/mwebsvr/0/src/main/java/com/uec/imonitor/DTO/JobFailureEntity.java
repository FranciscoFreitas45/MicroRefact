package com.uec.imonitor.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
public class JobFailureEntity {

 private  int innerid;

 private  String jobName;

 private  String tableName;

 private  String idSet;

 private  Date lastModifyTime;

 private  Integer num;


public void setJobName(String jobName){
    this.jobName = jobName;
}


public Date getLastModifyTime(){
    return lastModifyTime;
}


public int getInnerid(){
    return innerid;
}


public String getIdSet(){
    return idSet;
}


public void setNum(Integer num){
    this.num = num;
}


public String getJobName(){
    return jobName;
}


public String getTableName(){
    return tableName;
}


public Integer getNum(){
    return num;
}


}