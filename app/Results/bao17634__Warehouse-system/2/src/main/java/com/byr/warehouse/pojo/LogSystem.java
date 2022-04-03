package com.byr.warehouse.pojo;
 import javax.persistence;
import java.util.Date;
@Entity
public class LogSystem {

@Id
// 设置主键并且设置主键为自增
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int logRid;

@Column(length = 1024)
 private  String logMessage;

 private  Date logDate;


public int getLogRid(){
    return logRid;
}


public void setLogDate(Date logDate){
    this.logDate = logDate;
}


public void setLogRid(int logRid){
    this.logRid = logRid;
}


public Date getLogDate(){
    return logDate;
}


public String getLogMessage(){
    return logMessage;
}


public void setLogMessage(String logMessage){
    this.logMessage = logMessage;
}


}