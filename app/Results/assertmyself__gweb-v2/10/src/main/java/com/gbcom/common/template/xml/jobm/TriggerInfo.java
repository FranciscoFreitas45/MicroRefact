package com.gbcom.common.template.xml.jobm;
 import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
@XStreamAlias("trigger")
public class TriggerInfo {

@XStreamAsAttribute
 private  String triggerGroup;

@XStreamAsAttribute
 private  String triggerName;

@XStreamAsAttribute
 private  String begainTime;

@XStreamAsAttribute
 private  String endTime;

@XStreamAsAttribute
 private  long repeatInterval;

@XStreamAsAttribute
 private  String repeatCount;

@XStreamAsAttribute
 private  boolean isSample;

@XStreamAsAttribute
 private  String cronExpression;


public boolean isSample(){
    return isSample;
}


public void setTriggerName(String triggerName){
    this.triggerName = triggerName;
}


public void setRepeatInterval(long repeatInterval){
    this.repeatInterval = repeatInterval;
}


public void setTriggerGroup(String triggerGroup){
    this.triggerGroup = triggerGroup;
}


public long getRepeatInterval(){
    return repeatInterval;
}


public void setBegainTime(String begainTime){
    this.begainTime = begainTime;
}


public void setCronExpression(String cronExpression){
    this.cronExpression = cronExpression;
}


public String getTriggerName(){
    return triggerName;
}


public String getEndTime(){
    return endTime;
}


public String getCronExpression(){
    return cronExpression;
}


public String getBegainTime(){
    return begainTime;
}


public void setRepeatCount(String repeatCount){
    this.repeatCount = repeatCount;
}


public void setEndTime(String endTime){
    this.endTime = endTime;
}


public String getTriggerGroup(){
    return triggerGroup;
}


public String getRepeatCount(){
    return repeatCount;
}


public void setSample(boolean isSample){
    this.isSample = isSample;
}


}