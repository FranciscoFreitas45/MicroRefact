package com.fosun.fc.projects.creepers.dto;
 import java.io.Serializable;
public class CreepersErrorListDTO extends CreepersBaseDTOimplements Serializable{

 private  long serialVersionUID;

 private  String merName;

 private  String taskType;

 private  String errorDesc;

public CreepersErrorListDTO() {
}
public void setTaskType(String taskType){
    this.taskType = taskType;
}


public String getMerName(){
    return this.merName;
}


public void setMerName(String merName){
    this.merName = merName;
}


public String getErrorDesc(){
    return errorDesc;
}


public void setErrorDesc(String errorDesc){
    this.errorDesc = errorDesc;
}


public String getTaskType(){
    return taskType;
}


}