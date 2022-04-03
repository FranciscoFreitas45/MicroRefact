package com.uec.imonitor.task.bean;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
@Entity
@Table(name = "job_scheduled_task")
// 对应Hibernate的xml配置文件中的dynamic-insert;Default:false;将dynamic-insert设置为true,同样的保存,hibernate会动态生成SQL语句,没有值的字段不会出现在insert语句中,性能有所优化
@DynamicInsert(true)
// 对应Hibernate的xml配置文件中的dynamic-update;Default:false;只更新部分字段
@DynamicUpdate(true)
public class ScheduledTaskEntity {

 private  long serialVersionUID;

// 指明这个属性映射为数据库的主键
@Id
@Column(name = "innerid", nullable = false)
 private  int innerid;

@Column(name = "class_name")
 private  String className;

@Column(name = "trigger_start_time")
 private  Date triggerStartTime;

@Column(name = "interval_in_seconds")
 private  Integer intervalInSeconds;

@Column(name = "cron_expression")
 private  String cronExpression;

@Column(name = "last_execution_time")
 private  Date lastExecutionTime;

@Column(name = "identification")
 private  String identification;

@Column(name = "enable")
 private  Boolean enable;

@Column(name = "job_group_name")
 private  String groupName;

@Column(name = "priority")
 private  Integer priority;

@Column(name = "param")
 private  String param;


public String getParam(){
    return param;
}


public void setEnable(Boolean enable){
    this.enable = enable;
}


public Date getTriggerStartTime(){
    return triggerStartTime;
}


public void setParam(String param){
    this.param = param;
}


public void setIntervalInSeconds(Integer intervalInSeconds){
    this.intervalInSeconds = intervalInSeconds;
}


public void setClassName(String className){
    this.className = className;
}


public void setCronExpression(String cronExpression){
    this.cronExpression = cronExpression;
}


public void setInnerid(int innerid){
    this.innerid = innerid;
}


public Date getLastExecutionTime(){
    return lastExecutionTime;
}


public String getIdentification(){
    return identification;
}


public String getGroupName(){
    return groupName;
}


public void setGroupName(String groupName){
    this.groupName = groupName;
}


public String getCronExpression(){
    return cronExpression;
}


public void setLastExecutionTime(Date lastExecutionTime){
    this.lastExecutionTime = lastExecutionTime;
}


public int getInnerid(){
    return innerid;
}


public Boolean getEnable(){
    return enable;
}


public Integer getPriority(){
    return priority;
}


public void setTriggerStartTime(Date triggerStartTime){
    this.triggerStartTime = triggerStartTime;
}


public Integer getIntervalInSeconds(){
    return intervalInSeconds;
}


public void setIdentification(String identification){
    this.identification = identification;
}


public String getClassName(){
    return className;
}


public void setPriority(Integer priority){
    this.priority = priority;
}


}