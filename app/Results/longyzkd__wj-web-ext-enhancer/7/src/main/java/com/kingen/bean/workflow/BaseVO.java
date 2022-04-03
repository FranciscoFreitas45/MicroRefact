package com.kingen.bean.workflow;
 import java.io.Serializable;
import java.util.Map;
import javax.persistence.Transient;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
public class BaseVO implements Serializable{

 private  long serialVersionUID;

 public  String VACATION;

 public  String SALARY;

 public  String EXPENSE;

 public  String OTHER;

 public  String CANDIDATE;

 public  String ASSIGNEE;

 public  String RUNNING;

 public  String FINISHED;

 public  String PENDING;

 public  String WAITING_FOR_APPROVAL;

 public  String APPROVAL_SUCCESS;

 public  String APPROVAL_FAILED;

 private  Integer user_id;

 private  String title;

 private  String user_name;

 private  String businessType;

 private  String businessKey;

 private  Map<String,Object> task;

 private  Map<String,Object> processInstance;

 private  Map<String,Object> historicProcessInstance;

 private  Map<String,Object> historicTaskInstance;

 private  Map<String,Object> processDefinition;


@Transient
public String getExpense(){
    return EXPENSE;
}


@Transient
public Map<String,Object> getProcessInstance(){
    return processInstance;
}


public void setHistoricProcessInstance(Map<String,Object> historicProcessInstance){
    this.historicProcessInstance = historicProcessInstance;
}


public void setUser_name(String user_name){
    this.user_name = user_name;
}


public void setProcessInstance(Map<String,Object> processInstance){
    this.processInstance = processInstance;
}


@Transient
public long getSerialversionuid(){
    return serialVersionUID;
}


public void setTitle(String title){
    this.title = title;
}


@Transient
public String getBusinessType(){
    return businessType;
}


@Transient
public String getVacation(){
    return VACATION;
}


@Transient
public String getUser_name(){
    return user_name;
}


public void setProcessDefinition(Map<String,Object> processDefinition){
    this.processDefinition = processDefinition;
}


@Transient
public Map<String,Object> getHistoricTaskInstance(){
    return historicTaskInstance;
}


public void setHistoricTaskInstance(Map<String,Object> historicTaskInstance){
    this.historicTaskInstance = historicTaskInstance;
}


@Transient
public Integer getUser_id(){
    return user_id;
}


@Transient
public String getTitle(){
    return title;
}


public void setUser_id(Integer user_id){
    this.user_id = user_id;
}


@Transient
public String getBusinessKey(){
    return businessKey;
}


@Transient
public Map<String,Object> getProcessDefinition(){
    return processDefinition;
}


public void setTask(Map<String,Object> task){
    this.task = task;
}


@Transient
public Map<String,Object> getHistoricProcessInstance(){
    return historicProcessInstance;
}


@Transient
public String getSalary(){
    return SALARY;
}


public void setBusinessType(String businessType){
    this.businessType = businessType;
}


public void setBusinessKey(String businessKey){
    this.businessKey = businessKey;
}


@Transient
public Map<String,Object> getTask(){
    return task;
}


}