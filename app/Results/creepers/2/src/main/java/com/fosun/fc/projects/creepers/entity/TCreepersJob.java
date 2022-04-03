package com.fosun.fc.projects.creepers.entity;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "T_CREEPERS_JOB")
@NamedQuery(name = "TCreepersJob.findAll", query = "SELECT t FROM TCreepersJob t")
public class TCreepersJob {

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_JOB_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_JOB")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_JOB_ID_GENERATOR")
 private  long id;

@Column(name = "DESCRIPTION")
 private  String description;

@Column(name = "IS_CONCURRENT")
 private  String isConcurrent;

@Column(name = "JOB_CLASS")
 private  String jobClass;

@Column(name = "JOB_NAME")
 private  String jobName;

 private  String memo;

@Column(name = "METHOD_NAME")
 private  String methodName;

@Temporal(TemporalType.DATE)
@Column(name = "NEXT_TIME")
 private  Date nextTime;

@Temporal(TemporalType.DATE)
@Column(name = "PREVIOUS_TIME")
 private  Date previousTime;

@Column(name = "SPRING_ID")
 private  String springId;

@Temporal(TemporalType.DATE)
@Column(name = "START_TIME")
 private  Date startTime;

 private  String status;

@Column(name = "JOB_GROUP")
 private  String jobGroup;

@Column(name = "CRON_EXPRESSION")
 private  String cronExpression;

@Column(name = "INDEX_URL")
 private  String indexUrl;

@Column(name = "THREAD_NUM")
 private  int threadNum;

public TCreepersJob() {
}
public String getSpringId(){
    return this.springId;
}


public Date getPreviousTime(){
    return this.previousTime;
}


public void setMethodName(String methodName){
    this.methodName = methodName;
}


public void setPreviousTime(Date previousTime){
    this.previousTime = previousTime;
}


public long getId(){
    return this.id;
}


public void setDescription(String description){
    this.description = description;
}


public String getStatus(){
    return this.status;
}


public String getDescription(){
    return this.description;
}


public void setCronExpression(String cronExpression){
    this.cronExpression = cronExpression;
}


public Date getNextTime(){
    return this.nextTime;
}


public void setId(long id){
    this.id = id;
}


public void setNextTime(Date nextTime){
    this.nextTime = nextTime;
}


public Date getStartTime(){
    return this.startTime;
}


public void setStartTime(Date startTime){
    this.startTime = startTime;
}


public void setJobName(String jobName){
    this.jobName = jobName;
}


public String getMethodName(){
    return this.methodName;
}


public void setIsConcurrent(String isConcurrent){
    this.isConcurrent = isConcurrent;
}


public String getJobName(){
    return this.jobName;
}


public int getThreadNum(){
    return threadNum;
}


public String getIsConcurrent(){
    return this.isConcurrent;
}


public void setStatus(String status){
    this.status = status;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getMemo(){
    return this.memo;
}


public String getCronExpression(){
    return cronExpression;
}


public void setIndexUrl(String indexUrl){
    this.indexUrl = indexUrl;
}


public String getJobClass(){
    return this.jobClass;
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