package com.gbcom.common.template.xml.am;
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class AlarmTplBean implements Serializable{

 private  long serialVersionUID;

 private  int alarmCode;

 private  String alarmName;

 private  String alarmType;

 private  String alarmOid;

 private  boolean canClear;

 private  String clearOid;

 private  String alarmObject;

 private  String alarmDetailDesc;

 private  String alarmLevel;

 private  Map<String,String> alarmExtInfo;

 private  String alarmRaiseCondition;

 private  String alarmClearCondition;

 private  String alarmAffectRange;

 private  String alarmProposedRepairAction;

 private  int alarmRebootClear;

 private  List<AlarmReasonBean> reasons;

 private  Map<Integer,AlarmReasonBean> alarmReasons;

 private  String isMail;

 private  String process;


public String getAlarmDetailDesc(){
    return alarmDetailDesc;
}


public List<AlarmReasonBean> getReasons(){
    return reasons;
}


public String getAlarmName(){
    return alarmName;
}


public String getAlarmAffectRange(){
    return alarmAffectRange;
}


public String getAlarmLevel(){
    return alarmLevel;
}


public Map<Integer,AlarmReasonBean> getAlarmReasons(){
    return alarmReasons;
}


public void setReasons(List<AlarmReasonBean> reasons){
    this.reasons = reasons;
}


public boolean isCanClear(){
    return canClear;
}


public String getClearOid(){
    return clearOid;
}


public String getIsMail(){
    return isMail;
}


public void setAlarmCode(int alarmCode){
    this.alarmCode = alarmCode;
}


public Map<String,String> getAlarmExtInfo(){
    return alarmExtInfo;
}


public void setAlarmClearCondition(String alarmClearCondition){
    this.alarmClearCondition = alarmClearCondition;
}


public void setClearOid(String clearOid){
    this.clearOid = clearOid;
}


public void setAlarmRaiseCondition(String alarmRaiseCondition){
    this.alarmRaiseCondition = alarmRaiseCondition;
}


public void setAlarmAffectRange(String alarmAffectRange){
    this.alarmAffectRange = alarmAffectRange;
}


public int getAlarmCode(){
    return alarmCode;
}


public String getAlarmObject(){
    return alarmObject;
}


public String getAlarmClearCondition(){
    return alarmClearCondition;
}


public int getAlarmRebootClear(){
    return alarmRebootClear;
}


public void setAlarmName(String alarmName){
    this.alarmName = alarmName;
}


public void setAlarmDetailDesc(String alarmDetailDesc){
    this.alarmDetailDesc = alarmDetailDesc;
}


public String getAlarmOid(){
    return alarmOid;
}


public String getProcess(){
    return process;
}


public void setCanClear(boolean canClear){
    this.canClear = canClear;
}


public void setProcess(String process){
    this.process = process;
}


public long getSerialversionuid(){
    return serialVersionUID;
}


public String getAlarmRaiseCondition(){
    return alarmRaiseCondition;
}


public void setAlarmExtInfo(Map<String,String> alarmExtInfo){
    this.alarmExtInfo = alarmExtInfo;
}


public void setAlarmObject(String alarmObject){
    this.alarmObject = alarmObject;
}


public void setAlarmRebootClear(int alarmRebootClear){
    this.alarmRebootClear = alarmRebootClear;
}


public void setIsMail(String isMail){
    this.isMail = isMail;
}


public String getAlarmType(){
    return alarmType;
}


public void setAlarmType(String alarmType){
    this.alarmType = alarmType;
}


public void setAlarmProposedRepairAction(String alarmProposedRepairAction){
    this.alarmProposedRepairAction = alarmProposedRepairAction;
}


public void setAlarmReasons(Map<Integer,AlarmReasonBean> alarmReasons){
    this.alarmReasons = alarmReasons;
}


public void setAlarmLevel(String alarmLevel){
    this.alarmLevel = alarmLevel;
}


public String getAlarmProposedRepairAction(){
    return alarmProposedRepairAction;
}


public void setAlarmOid(String alarmOid){
    this.alarmOid = alarmOid;
}


}