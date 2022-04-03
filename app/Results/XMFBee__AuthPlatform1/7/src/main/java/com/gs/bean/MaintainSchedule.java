package com.gs.bean;
 import java.util.Date;
import com.gs.Interface.MaintainRecord;
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


public void setMaintainRecordId(String maintainRecordId){
    this.maintainRecordId = maintainRecordId;
}


public MaintainRecord getMaintainRecord(){
    return maintainRecord;
}


public void setMsStatus(String msStatus){
    this.msStatus = msStatus;
}


public void setCurrentStatus(String currentStatus){
    this.currentStatus = currentStatus;
}


public String getMaintainRecordId(){
    return maintainRecordId;
}


public void setMsCreatedTime(Date msCreatedTime){
    this.msCreatedTime = msCreatedTime;
}


public String getMaintainScheduleDes(){
    return maintainScheduleDes;
}


public void setMaintainRecord(MaintainRecord maintainRecord){
    this.maintainRecord = maintainRecord;
}


public Date getMsCreatedTime(){
    return msCreatedTime;
}


public String getMsStatus(){
    return msStatus;
}


@Override
public String toString(){
    return "MaintainSchedule{" + "maintainScheduleId='" + maintainScheduleId + '\'' + ", maintainRecordId='" + maintainRecordId + '\'' + ", maintainScheduleDes='" + maintainScheduleDes + '\'' + ", msCreatedTime=" + msCreatedTime + ", msStatus='" + msStatus + '\'' + '}';
}


public String getCurrentStatus(){
    return currentStatus;
}


public void setMaintainScheduleId(String maintainScheduleId){
    this.maintainScheduleId = maintainScheduleId;
}


public void setMaintainScheduleDes(String maintainScheduleDes){
    this.maintainScheduleDes = maintainScheduleDes;
}


}