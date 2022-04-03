package com.uec.imonitor.task.bean;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
@Entity
@Table(name = "job_failure")
public class JobFailureEntity {

// 指明这个属性映射为数据库的主键
@Id
@GeneratedValue
@Column(name = "innerid", nullable = false)
 private  int innerid;

@Column(name = "job_name")
 private  String jobName;

@Column(name = "table_name")
 private  String tableName;

@Column(name = "id_set")
 private  String idSet;

@Column(name = "last_modify_time")
 private  Date lastModifyTime;

@Column(name = "num")
 private  Integer num;


public void setInnerid(int innerid){
    this.innerid = innerid;
}


public void setJobName(String jobName){
    this.jobName = jobName;
}


public void setTableName(String tableName){
    this.tableName = tableName;
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


public void setLastModifyTime(Date lastModifyTime){
    this.lastModifyTime = lastModifyTime;
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


public void setIdSet(String idSet){
    this.idSet = idSet;
}


public Integer getNum(){
    return num;
}


}