package com.crontab;
 import java.util.Map;
public class JsonJobDefinition {

 private  String key;

 private  String jobName;

 private  String triggerName;

 private  String jobClass;

 private  String triggerExpression;

 private  Map<String,String> jobData;


public void setJobName(String jobName){
    this.jobName = jobName;
}


public Map<String,String> getJobData(){
    return jobData;
}


public String getKey(){
    return key;
}


public String getTriggerName(){
    return triggerName;
}


public void setTriggerName(String triggerName){
    this.triggerName = triggerName;
}


public String getTriggerExpression(){
    return triggerExpression;
}


public String getJobName(){
    return jobName;
}


public void setJobData(Map<String,String> jobData){
    this.jobData = jobData;
}


public String getJobClass(){
    return jobClass;
}


public void setTriggerExpression(String triggerExpression){
    this.triggerExpression = triggerExpression;
}


public void setJobClass(String jobClass){
    this.jobClass = jobClass;
}


public void setKey(String key){
    this.key = key;
}


}