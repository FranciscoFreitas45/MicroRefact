package com.gs.DTO;
 import java.util.Arrays;
import java.util.Date;
import com.gs.Interface.User;
import com.gs.Interface.Company;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://12";


public User getUser(){
    return user;
}


public IncomingType getIncomingType(){
    return incomingType;
}


public OutgoingType getOutgoingType(){
    return outgoingType;
}


public String getInOutCreatedUser(){
    return inOutCreatedUser;
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


public void setInOutMoney(Double inOutMoney){
    this.inOutMoney = inOutMoney;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setInOutMoney"))

.queryParam("inOutMoney",inOutMoney)
;
restTemplate.put(builder.toUriString(),null);
}


public void setInOutCreatedUser(String inOutCreatedUser){
    this.inOutCreatedUser = inOutCreatedUser;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setInOutCreatedUser"))

.queryParam("inOutCreatedUser",inOutCreatedUser)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOutTypeId(String outTypeId){
    this.outTypeId = outTypeId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOutTypeId"))

.queryParam("outTypeId",outTypeId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCompanyId(String companyId){
    this.companyId = companyId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCompanyId"))

.queryParam("companyId",companyId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setInTypeId(String inTypeId){
    this.inTypeId = inTypeId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setInTypeId"))

.queryParam("inTypeId",inTypeId)
;
restTemplate.put(builder.toUriString(),null);
}


}