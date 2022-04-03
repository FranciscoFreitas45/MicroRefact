package com.ipe.module.bpm.controller.pojo;
 import org.activiti.engine.repository.ProcessDefinition;
public class ActProcessDef implements ProcessDefinition{

 private  String id;

 private  String rev;

 private  String category;

 private  String name;

 private  String key;

 private  int version;

 private  String resourceName;

 private  String diagramResourceName;

 private  String description;

 private  String deploymentId;

 private  boolean startFormKey;

 private  boolean suspended;


public void setName(String name){
    this.name = name;
}


public int getVersion(){
    return version;
}


public String getKey(){
    return key;
}


public void setRev(String rev){
    this.rev = rev;
}


public String getName(){
    return name;
}


public boolean isSuspended(){
    return suspended;
}


public String getRev(){
    return rev;
}


public void setVersion(int version){
    this.version = version;
}


public void setDiagramResourceName(String diagramResourceName){
    this.diagramResourceName = diagramResourceName;
}


public String getCategory(){
    return category;
}


public String getResourceName(){
    return resourceName;
}


public String getId(){
    return id;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public void setStartFormKey(boolean startFormKey){
    this.startFormKey = startFormKey;
}


public void setSuspended(boolean suspended){
    this.suspended = suspended;
}


public void setDeploymentId(String deploymentId){
    this.deploymentId = deploymentId;
}


public void setResourceName(String resourceName){
    this.resourceName = resourceName;
}


public void setCategory(String category){
    this.category = category;
}


public void setId(String id){
    this.id = id;
}


public boolean hasStartFormKey(){
    return startFormKey;
}


public String getDiagramResourceName(){
    return diagramResourceName;
}


public void setKey(String key){
    this.key = key;
}


public String getDeploymentId(){
    return deploymentId;
}


}