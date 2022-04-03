package com.gs.bean;
 import java.util.Date;
import com.gs.Interface.Checkin;
public class MaintainRemind {

 private  String remindId;

 private  String userId;

 private  Date lastMaintainTime;

 private  Double lastMaintainMileage;

 private  String remindMsg;

 private  Date remindTime;

 private  String remindType;

 private  Date remindCreatedTime;

 private  String companyId;

 private  Checkin checkin;

 private  Company company;


public void setRemindType(String remindType){
    this.remindType = remindType;
}


public String getRemindId(){
    return remindId;
}


public String getRemindMsg(){
    return remindMsg;
}


public Date getRemindTime(){
    return remindTime;
}


public void setRemindCreatedTime(Date remindCreatedTime){
    this.remindCreatedTime = remindCreatedTime;
}


public void setRemindId(String remindId){
    this.remindId = remindId;
}


public Date getRemindCreatedTime(){
    return remindCreatedTime;
}


public Date getLastMaintainTime(){
    return lastMaintainTime;
}


public void setRemindTime(Date remindTime){
    this.remindTime = remindTime;
}


public Company getCompany(){
    return company;
}


public void setLastMaintainMileage(Double lastMaintainMileage){
    this.lastMaintainMileage = lastMaintainMileage;
}


public String getRemindType(){
    return remindType;
}


public String getCompanyId(){
    return companyId;
}


public void setCheckin(Checkin checkin){
    this.checkin = checkin;
}


public void setLastMaintainTime(Date lastMaintainTime){
    this.lastMaintainTime = lastMaintainTime;
}


public void setRemindMsg(String remindMsg){
    this.remindMsg = remindMsg;
}


public Checkin getCheckin(){
    return checkin;
}


public Double getLastMaintainMileage(){
    return lastMaintainMileage;
}


public void setCompanyId(String companyId){
    this.companyId = companyId;
}


public void setCompany(Company company){
    this.company = company;
}


public String getUserId(){
    return userId;
}


public void setUserId(String userId){
    this.userId = userId;
}


}