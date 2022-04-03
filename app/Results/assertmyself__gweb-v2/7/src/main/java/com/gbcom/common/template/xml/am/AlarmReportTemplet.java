package com.gbcom.common.template.xml.am;
 import java.io.Serializable;
import javax.xml.bind.annotation.XmlType;
@XmlType(propOrder = { "vendor", "sysTime", "alarmOid", "alarmSn", "alarmNeName", "alarmLevel", "alarmType", "alarmReasonID", "alarmReason", "alarmRaiseTime", "alarmStatus", "alarmTitle", "alarmInfo" })
public class AlarmReportTemplet implements Serializable{

 private  long serialVersionUID;

 private  String vendor;

 private  String sysTime;

 private  String alarmOid;

 private  String alarmSn;

 private  String alarmNeName;

 private  String alarmLevel;

 private  String alarmType;

 private  String alarmReasonID;

 private  String alarmReason;

 private  String alarmRaiseTime;

 private  String alarmStatus;

 private  String alarmTitle;

 private  String alarmInfo;


public String getAlarmLevel(){
    return alarmLevel;
}


public void setAlarmTitle(String alarmTitle){
    this.alarmTitle = alarmTitle;
}


public void setAlarmInfo(String alarmInfo){
    this.alarmInfo = alarmInfo;
}


public String getAlarmRaiseTime(){
    return alarmRaiseTime;
}


public String getAlarmStatus(){
    return alarmStatus;
}


public String getVendor(){
    return vendor;
}


public void setAlarmSn(String alarmSn){
    this.alarmSn = alarmSn;
}


public void setSysTime(String sysTime){
    this.sysTime = sysTime;
}


public String getAlarmNeName(){
    return alarmNeName;
}


public void setAlarmReasonID(String alarmReasonID){
    this.alarmReasonID = alarmReasonID;
}


public void setVendor(String vendor){
    this.vendor = vendor;
}


public String getAlarmReasonID(){
    return alarmReasonID;
}


public String getAlarmReason(){
    return alarmReason;
}


public void setAlarmRaiseTime(String alarmRaiseTime){
    this.alarmRaiseTime = alarmRaiseTime;
}


public String getAlarmTitle(){
    return alarmTitle;
}


public String getAlarmOid(){
    return alarmOid;
}


public String getAlarmInfo(){
    return alarmInfo;
}


public void setAlarmStatus(String alarmStatus){
    this.alarmStatus = alarmStatus;
}


public String getAlarmType(){
    return alarmType;
}


public void setAlarmType(String alarmType){
    this.alarmType = alarmType;
}


public String getSysTime(){
    return sysTime;
}


public String getAlarmSn(){
    return alarmSn;
}


public void setAlarmReason(String alarmReason){
    this.alarmReason = alarmReason;
}


public void setAlarmNeName(String alarmNeName){
    this.alarmNeName = alarmNeName;
}


public void setAlarmLevel(String alarmLevel){
    this.alarmLevel = alarmLevel;
}


public void setAlarmOid(String alarmOid){
    this.alarmOid = alarmOid;
}


}