package com.fosun.fc.projects.creepers.dto;
 import java.util.Date;
public class CreepersJobDTO extends CreepersBaseDTO{

 public  String STATUS_RUNNING;

 public  String STATUS_NOT_RUNNING;

 public  String CONCURRENT_IS;

 public  String CONCURRENT_NOT;

 private  long serialVersionUID;

 private  String description;

 private  String isConcurrent;

 private  String jobClass;

 private  String jobName;

 private  String memo;

 private  String methodName;

 private  Date nextTime;

 private  Date previousTime;

 private  String springId;

 private  Date startTime;

 private  String status;

 private  String jobGroup;

 private  String cronExpression;

 private  String indexUrl;

 private  int threadNum;

public CreepersJobDTO() {
}
public String getSpringId(){
    return springId;
}


public Date getPreviousTime(){
    return previousTime;
}


public void setMethodName(String methodName){
    this.methodName = methodName;
}


public void setPreviousTime(Date previousTime){
    this.previousTime = previousTime;
}


public void setDescription(String description){
    this.description = description;
}


public String getStatus(){
    return status;
}


public String getDescription(){
    return description;
}


public void setCronExpression(String cronExpression){
    this.cronExpression = cronExpression;
}


public Date getNextTime(){
    return nextTime;
}


public void setNextTime(Date nextTime){
    this.nextTime = nextTime;
}


public Date getStartTime(){
    return startTime;
}


public void setStartTime(Date startTime){
    this.startTime = startTime;
}


public void setJobName(String jobName){
    this.jobName = jobName;
}


public String getMethodName(){
    return methodName;
}


public void setIsConcurrent(String isConcurrent){
    this.isConcurrent = isConcurrent;
}


public String getJobName(){
    return jobName;
}


public int getThreadNum(){
    return threadNum;
}


public String getIsConcurrent(){
    return isConcurrent;
}


public void setStatus(String status){
    this.status = status;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getMemo(){
    return memo;
}


public String getCronExpression(){
    return cronExpression;
}


public void setIndexUrl(String indexUrl){
    this.indexUrl = indexUrl;
}


public String getJobClass(){
    return jobClass;
}


public void setThreadNum(int threadNum){
    this.threadNum = threadNum;
}


public String getJobGroup(){
    return jobGroup;
}


public void setJobClass(String jobClass){
    this.jobClass = jobClass;
}


public void setSpringId(String springId){
    this.springId = springId;
}


public String getIndexUrl(){
    return indexUrl;
}


public void setJobGroup(String jobGroup){
    this.jobGroup = jobGroup;
}


}