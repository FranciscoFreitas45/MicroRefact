package com.gbcom.common.template.xml.jobm;
 import java.util.List;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
@XStreamAlias("JobWrapper")
public class JobWrapperInfo {

@XStreamAsAttribute
 private  String jobGroup;

@XStreamAsAttribute
 private  String jobName;

@XStreamAsAttribute
 private  String jobClass;

@XStreamAlias("triggerList")
 private  List<TriggerInfo> triggerList;

 private  List<JobParam> jobParamList;


public void setJobName(String jobName){
    this.jobName = jobName;
}


public void setTriggerList(List<TriggerInfo> triggerList){
    this.triggerList = triggerList;
}


public void setJobParamList(List<JobParam> jobParamList){
    this.jobParamList = jobParamList;
}


public String getJobName(){
    return jobName;
}


public String getJobClass(){
    return jobClass;
}


public List<JobParam> getJobParamList(){
    return jobParamList;
}


public String getJobGroup(){
    return jobGroup;
}


public List<TriggerInfo> getTriggerList(){
    return triggerList;
}


public void setJobClass(String jobClass){
    this.jobClass = jobClass;
}


public void setJobGroup(String jobGroup){
    this.jobGroup = jobGroup;
}


}