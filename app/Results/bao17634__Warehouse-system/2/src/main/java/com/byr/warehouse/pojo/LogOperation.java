package com.byr.warehouse.pojo;
 import javax.persistence;
import java.util.Date;
@Entity
public class LogOperation {

@Id
// 设置主键并且设置主键为自增
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int logRid;

 private  String username;

 private  String operation;

 private  String result;

@Column(length = 1024)
 private  String detail;

 private  Date date;


public int getLogRid(){
    return logRid;
}


public void setUsername(String username){
    this.username = username;
}


public void setResult(String result){
    this.result = result;
}


public void setDetail(String detail){
    this.detail = detail;
}


public String getDetail(){
    return detail;
}


public String getResult(){
    return result;
}


public void setLogRid(int logRid){
    this.logRid = logRid;
}


public String getOperation(){
    return operation;
}


public void setOperation(String operation){
    this.operation = operation;
}


public void setDate(Date date){
    this.date = date;
}


public Date getDate(){
    return date;
}


public String getUsername(){
    return username;
}


}