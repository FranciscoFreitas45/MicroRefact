package com.netease.dto;
 import java.util.Date;
import com.netease.enums.CredentialType;
public class BasicInfoDTO {

 private  String reportNumber;

 private  Date queryTime;

 private  Date reportTime;

 private  String name;

 private  CredentialType credentialType;

 private  String credentialNumber;

 private  int isMarried;


public void setReportTime(Date reportTime){
    this.reportTime = reportTime;
}


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setCredentialType(CredentialType credentialType){
    this.credentialType = credentialType;
}


public void setCredentialNumber(String credentialNumber){
    this.credentialNumber = credentialNumber;
}


public Date getQueryTime(){
    return queryTime;
}


public Date getReportTime(){
    return reportTime;
}


public void setReportNumber(String reportNumber){
    this.reportNumber = reportNumber;
}


public int getIsMarried(){
    return isMarried;
}


public void setIsMarried(int isMarried){
    this.isMarried = isMarried;
}


public String getCredentialNumber(){
    return credentialNumber;
}


public void setQueryTime(Date queryTime){
    this.queryTime = queryTime;
}


public CredentialType getCredentialType(){
    return credentialType;
}


public String getReportNumber(){
    return reportNumber;
}


}