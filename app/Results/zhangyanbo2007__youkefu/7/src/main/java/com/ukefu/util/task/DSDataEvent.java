package com.ukefu.util.task;
 import java.util.HashMap;
import java.util.Map;
public class DSDataEvent {

 public  DSData dsData;

 private  String orgi;

 private  String tablename;

 private  String batid;

 private  Map<String,Object> values;

 private  boolean failures;

 private  long times;

 private  String message;


public long getTimes(){
    return times;
}


public DSData getDSData(){
    return dsData;
}


public Map<String,Object> getValues(){
    return values;
}


public String getMessage(){
    return message;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


public String getBatid(){
    return batid;
}


public void setMessage(String message){
    this.message = message;
}


public void setBatid(String batid){
    this.batid = batid;
}


public String getTablename(){
    return tablename;
}


public void setTablename(String tablename){
    this.tablename = tablename;
}


public boolean isFailures(){
    return failures;
}


public void setValues(Map<String,Object> values){
    this.values = values;
}


public void setFailures(boolean failures){
    this.failures = failures;
}


public String getOrgi(){
    return orgi;
}


public void setDSData(DSData dsData){
    this.dsData = dsData;
}


public void setTimes(long times){
    this.times = times;
}


}