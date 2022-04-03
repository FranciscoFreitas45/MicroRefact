package com.gs.bean.view;
 import com.gs.bean.MaterialUse;
import com.gs.bean.WorkInfo;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntity;
import org.activiti.engine.impl.persistence.entity.HistoricTaskInstanceEntity;
import org.activiti.engine.runtime.ProcessInstance;
import java.util.Map;
import com.gs.Interface.WorkInfo;
public class MaterialURTemp extends MaterialUse{

 private  String flag;

 private  WorkInfo workInfo;

 private  String processInstanceId;

 private  String reqMsg;

 private  String respMsg;

 private  String taskId;

 private  HistoricTaskInstanceEntity taskTemp;

 private  HistoricProcessInstanceEntity processInstance;

 private  Map varsMap;


public void setWorkInfo(WorkInfo workInfo){
    this.workInfo = workInfo;
}


public HistoricProcessInstanceEntity getProcessInstance(){
    return processInstance;
}


public String getTaskId(){
    return taskId;
}


public void setFlag(String flag){
    this.flag = flag;
}


public void setProcessInstance(HistoricProcessInstanceEntity processInstance){
    this.processInstance = processInstance;
}


public void setRespMsg(String respMsg){
    this.respMsg = respMsg;
}


public HistoricTaskInstanceEntity getTaskTemp(){
    return taskTemp;
}


public void setTaskId(String taskId){
    this.taskId = taskId;
}


public String getRespMsg(){
    return respMsg;
}


public void setVarsMap(Map varsMap){
    this.varsMap = varsMap;
}


public Map getVarsMap(){
    return varsMap;
}


public void setProcessInstanceId(String processInstanceId){
    this.processInstanceId = processInstanceId;
}


public String getReqMsg(){
    return reqMsg;
}


public String getProcessInstanceId(){
    return processInstanceId;
}


public void setTaskTemp(HistoricTaskInstanceEntity taskTemp){
    this.taskTemp = taskTemp;
}


public String getFlag(){
    return flag;
}


public WorkInfo getWorkInfo(){
    return workInfo;
}


public void setReqMsg(String reqMsg){
    this.reqMsg = reqMsg;
}


}