package com.jeecg.demo.entity;
 import java.util.Date;
public class JeecgLogReport {

 private  String id;

 private  String name;

 private  int ct;

 private  int loginct;

 private  int outct;

 private  int xgct;

 private  Date beginDate;

 private  Date endDate;

 private  int value;

 private  String color;


public void setName(String name){
    this.name = name;
}


public void setOutct(int outct){
    this.outct = outct;
}


public String getName(){
    return name;
}


public String getColor(){
    return color;
}


public String getId(){
    return id;
}


public Date getEndDate(){
    return endDate;
}


public void setEndDate(Date endDate){
    this.endDate = endDate;
}


public void setXgct(int xgct){
    this.xgct = xgct;
}


public int getXgct(){
    return xgct;
}


public int getLoginct(){
    return loginct;
}


public int getValue(){
    return value;
}


public Date getBeginDate(){
    return beginDate;
}


public void setBeginDate(Date beginDate){
    this.beginDate = beginDate;
}


public void setColor(String color){
    this.color = color;
}


public void setCt(int ct){
    this.ct = ct;
}


public void setValue(int value){
    this.value = value;
}


public int getOutct(){
    return outct;
}


public void setId(String id){
    this.id = id;
}


public int getCt(){
    return ct;
}


public void setLoginct(int loginct){
    this.loginct = loginct;
}


}