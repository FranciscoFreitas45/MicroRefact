package org.jeecgframework.web.system.pojo.base;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "t_s_timetask", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TSTimeTaskEntity {

 private  java.lang.String id;

 private  java.lang.String taskId;

 private  java.lang.String taskDescribe;

 private  java.lang.String cronExpression;

 private  java.lang.String isEffect;

 private  java.lang.String isStart;

 private  java.util.Date createDate;

 private  java.lang.String createBy;

 private  java.lang.String createName;

 private  java.util.Date updateDate;

 private  java.lang.String updateBy;

 private  java.lang.String updateName;

 private  java.lang.String className;

 private  java.lang.String runServerIp;

 private  java.lang.String runServer;


@Column(name = "CREATE_NAME", nullable = true, length = 32)
public java.lang.String getCreateName(){
    return this.createName;
}


public void setUpdateName(java.lang.String updateName){
    this.updateName = updateName;
}


public void setTaskId(java.lang.String taskId){
    this.taskId = taskId;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "ID", nullable = false, length = 32)
public java.lang.String getId(){
    return this.id;
}


@Column(name = "RUN_SERVER_IP", nullable = false, length = 15)
public java.lang.String getRunServerIp(){
    return runServerIp;
}


public void setClassName(java.lang.String className){
    this.className = className;
}


public void setCronExpression(java.lang.String cronExpression){
    this.cronExpression = cronExpression;
}


public void setRunServer(java.lang.String runServer){
    this.runServer = runServer;
}


@Column(name = "CREATE_DATE", nullable = true)
public java.util.Date getCreateDate(){
    return this.createDate;
}


public void setCreateName(java.lang.String createName){
    this.createName = createName;
}


@Column(name = "UPDATE_DATE", nullable = true)
public java.util.Date getUpdateDate(){
    return this.updateDate;
}


public void setId(java.lang.String id){
    this.id = id;
}


@Column(name = "UPDATE_BY", nullable = true, length = 32)
public java.lang.String getUpdateBy(){
    return this.updateBy;
}


@Column(name = "TASK_DESCRIBE", nullable = false, length = 50)
public java.lang.String getTaskDescribe(){
    return this.taskDescribe;
}


@Column(name = "RUN_SERVER", nullable = false, length = 300)
public java.lang.String getRunServer(){
    return runServer;
}


@Column(name = "TASK_ID", nullable = false, length = 100)
public java.lang.String getTaskId(){
    return this.taskId;
}


@Column(name = "IS_START", nullable = false, length = 1)
public java.lang.String getIsStart(){
    return this.isStart;
}


public void setUpdateDate(java.util.Date updateDate){
    this.updateDate = updateDate;
}


public void setUpdateBy(java.lang.String updateBy){
    this.updateBy = updateBy;
}


public void setCreateBy(java.lang.String createBy){
    this.createBy = createBy;
}


public void setIsStart(java.lang.String isStart){
    this.isStart = isStart;
}


public void setRunServerIp(java.lang.String runServerIp){
    this.runServerIp = runServerIp;
}


@Column(name = "UPDATE_NAME", nullable = true, length = 32)
public java.lang.String getUpdateName(){
    return this.updateName;
}


@Column(name = "CRON_EXPRESSION", nullable = false, length = 100)
public java.lang.String getCronExpression(){
    return this.cronExpression;
}


@Column(name = "CREATE_BY", nullable = true, length = 32)
public java.lang.String getCreateBy(){
    return this.createBy;
}


public void setCreateDate(java.util.Date createDate){
    this.createDate = createDate;
}


public void setTaskDescribe(java.lang.String taskDescribe){
    this.taskDescribe = taskDescribe;
}


@Column(name = "CLASS_NAME", nullable = false, length = 300)
public java.lang.String getClassName(){
    return className;
}


public void setIsEffect(java.lang.String isEffect){
    this.isEffect = isEffect;
}


@Column(name = "IS_EFFECT", nullable = false, length = 1)
public java.lang.String getIsEffect(){
    return this.isEffect;
}


}