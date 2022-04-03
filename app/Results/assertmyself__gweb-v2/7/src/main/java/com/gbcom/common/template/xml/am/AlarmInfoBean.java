package com.gbcom.common.template.xml.am;
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
public class AlarmInfoBean implements Serializable{

 private  long serialVersionUID;

 private  int alarmCode;

 private  String alarmName;

 private  String alarmType;

 private  String alarmOid;

 private  boolean canClear;

 private  String clearOid;

 private  String alarmObject;

 private  String alarmDetailDesc;

 private  int alarmLevel;

 private  Map<String,String> alarmExtInfo;

 private  String alarmRaiseCondition;

 private  String alarmClearCondition;

 private  String alarmAffectRange;

 private  String alarmProposedRepairAction;

 private  int alarmRebootClear;

 private  List<AlarmReasonBean> reasons;

 private  Map<Integer,AlarmReasonBean> alarmReasons;

 private  boolean mail;

 private  String process;


public String getAlarmDetailDesc(){
    return alarmDetailDesc;
}


@XmlElementWrapper(name = "reasons")
@XmlElement(name = "reason")
public List<AlarmReasonBean> getReasons(){
    return reasons;
}


public String getAlarmName(){
    return alarmName;
}


public String getAlarmAffectRange(){
    return alarmAffectRange;
}


public int getAlarmLevel(){
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


public boolean isMail(){
    return mail;
}


public void setProcess(String process){
    this.process = process;
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


public String getAlarmType(){
    return alarmType;
}


public void setAlarmType(String alarmType){
    this.alarmType = alarmType;
}


public void setAlarmProposedRepairAction(String alarmProposedRepairAction){
    this.alarmProposedRepairAction = alarmProposedRepairAction;
}


public void setMail(boolean mail){
    this.mail = mail;
}


public void initAlarmReasonMap(){
    for (AlarmReasonBean alarmReasonBean : reasons) {
        alarmReasons.put(alarmReasonBean.getCode(), alarmReasonBean);
    }
}


public void setAlarmReasons(Map<Integer,AlarmReasonBean> alarmReasons){
    this.alarmReasons = alarmReasons;
}


public void setAlarmLevel(int alarmLevel){
    this.alarmLevel = alarmLevel;
}


public String getAlarmProposedRepairAction(){
    return alarmProposedRepairAction;
}


@Override
public String toString(){
    return "AlarmInfoBean [alarmAffectRange=" + alarmAffectRange + ", alarmClearCondition=" + alarmClearCondition + ", alarmCode=" + alarmCode + ", alarmDetailDesc=" + alarmDetailDesc + ", alarmExtInfo=" + alarmExtInfo + ", alarmLevel=" + alarmLevel + ", alarmName=" + alarmName + ", alarmObject=" + alarmObject + ", alarmOid=" + alarmOid + ", alarmProposedRepairAction=" + alarmProposedRepairAction + ", alarmRaiseCondition=" + alarmRaiseCondition + ", alarmReasons=" + alarmReasons + ", alarmRebootClear=" + alarmRebootClear + ", alarmType=" + alarmType + ", canClear=" + canClear + ", clearOid=" + clearOid + ", mail=" + mail + ", process=" + process + ", reasons=" + reasons + "]";
}


public void setAlarmOid(String alarmOid){
    this.alarmOid = alarmOid;
}


}