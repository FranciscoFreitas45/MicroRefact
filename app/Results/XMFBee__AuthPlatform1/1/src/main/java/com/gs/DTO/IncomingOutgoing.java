package com.gs.DTO;
 import java.util.Arrays;
import java.util.Date;
public class IncomingOutgoing {

 private  String inOutId;

 private  String inTypeId;

 private  String outTypeId;

 private  Double inOutMoney;

 private  String inOutCreatedUser;

 private  Date inOutCreatedTime;

 private  String inOutStatus;

 private  String companyId;

 private  double[] outMoneys;

 private  double[] inMoneys;

 private  OutgoingType outgoingType;

 private  IncomingType incomingType;

 private  User user;

 private  Company company;


public void setIncomingType(IncomingType incomingType){
    this.incomingType = incomingType;
}


public User getUser(){
    return user;
}


public IncomingType getIncomingType(){
    return incomingType;
}


public OutgoingType getOutgoingType(){
    return outgoingType;
}


public void setInOutCreatedTime(Date inOutCreatedTime){
    this.inOutCreatedTime = inOutCreatedTime;
}


public void setInOutMoney(Double inOutMoney){
    this.inOutMoney = inOutMoney;
}


public String getInOutCreatedUser(){
    return inOutCreatedUser;
}


public void setCompany(Company company){
    this.company = company;
}


public Date getInOutCreatedTime(){
    return inOutCreatedTime;
}


public double[] getOutMoneys(){
    return outMoneys;
}


public String getInTypeId(){
    return inTypeId;
}


public String getOutTypeId(){
    return outTypeId;
}


public void setInTypeId(String inTypeId){
    this.inTypeId = inTypeId;
}


public Double getInOutMoney(){
    return inOutMoney;
}


public String getInOutId(){
    return inOutId;
}


public Company getCompany(){
    return company;
}


public String getCompanyId(){
    return companyId;
}


public double[] getInMoneys(){
    return inMoneys;
}


public String getInOutStatus(){
    return inOutStatus;
}


public void setCompanyId(String companyId){
    this.companyId = companyId;
}


}