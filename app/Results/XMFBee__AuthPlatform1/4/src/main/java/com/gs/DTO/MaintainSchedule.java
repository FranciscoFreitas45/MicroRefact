package com.gs.DTO;
 import java.util.Date;
public class MaintainSchedule {

 private  String maintainScheduleId;

 private  String maintainRecordId;

 private  String maintainScheduleDes;

 private  Date msCreatedTime;

 private  String msStatus;

 private  String currentStatus;

 private  MaintainRecord maintainRecord;


public String getMaintainScheduleId(){
    return maintainScheduleId;
}


public MaintainRecord getMaintainRecord(){
    return maintainRecord;
}


public void setCurrentStatus(String currentStatus){
    this.currentStatus = currentStatus;
}


public String getMaintainRecordId(){
    return maintainRecordId;
}


public String getMaintainScheduleDes(){
    return maintainScheduleDes;
}


public Date getMsCreatedTime(){
    return msCreatedTime;
}


public String getMsStatus(){
    return msStatus;
}


public String getCurrentStatus(){
    return currentStatus;
}


public void setMaintainScheduleDes(String maintainScheduleDes){
    this.maintainScheduleDes = maintainScheduleDes;
}


}