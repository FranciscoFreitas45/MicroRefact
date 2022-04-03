package com.fosun.fc.projects.creepers.DTO;
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


public String getStatus(){
    return status;
}


public String getDescription(){
    return description;
}


public Date getNextTime(){
    return nextTime;
}


public Date getStartTime(){
    return startTime;
}


public String getMethodName(){
    return methodName;
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


public String getMemo(){
    return memo;
}


public String getCronExpression(){
    return cronExpression;
}


public String getJobClass(){
    return jobClass;
}


public String getJobGroup(){
    return jobGroup;
}


public String getIndexUrl(){
    return indexUrl;
}


}