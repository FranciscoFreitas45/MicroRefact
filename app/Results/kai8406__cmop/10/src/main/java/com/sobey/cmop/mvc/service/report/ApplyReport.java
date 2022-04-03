package com.sobey.cmop.mvc.service.report;
 import java.math.BigDecimal;
public class ApplyReport {

 private  long serialVersionUID;

 private  String title;

 private  String createTime;

 private  String userName;

 private  String priority;

 private  String serviceStart;

 private  String serviceEnd;

 private  String description;

 private  BigDecimal servicesCost;


public String getCreateTime(){
    return createTime;
}


public void setServiceStart(String serviceStart){
    this.serviceStart = serviceStart;
}


public void setTitle(String title){
    this.title = title;
}


public void setCreateTime(String createTime){
    this.createTime = createTime;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public BigDecimal getServicesCost(){
    return servicesCost;
}


public String getServiceEnd(){
    return serviceEnd;
}


public String getTitle(){
    return title;
}


public void setUserName(String userName){
    this.userName = userName;
}


public String getPriority(){
    return priority;
}


public void setServicesCost(BigDecimal servicesCost){
    this.servicesCost = servicesCost;
}


public String getUserName(){
    return userName;
}


public String getServiceStart(){
    return serviceStart;
}


public void setPriority(String priority){
    this.priority = priority;
}


public void setServiceEnd(String serviceEnd){
    this.serviceEnd = serviceEnd;
}


}