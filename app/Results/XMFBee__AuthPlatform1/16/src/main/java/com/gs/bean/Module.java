package com.gs.bean;
 import java.util.List;
public class Module {

 private  String moduleId;

 private  String moduleName;

 private  String moduleDes;

 private  String moduleStatus;

 private  List<Permission> permissions;


public void setModuleStatus(String moduleStatus){
    this.moduleStatus = moduleStatus;
}


public void setModuleId(String moduleId){
    this.moduleId = moduleId;
}


public List<Permission> getPermissions(){
    return permissions;
}


public void setModuleDes(String moduleDes){
    this.moduleDes = moduleDes;
}


public String getModuleStatus(){
    return moduleStatus;
}


public String getModuleDes(){
    return moduleDes;
}


public String getModuleId(){
    return moduleId;
}


public void setPermissions(List<Permission> permissions){
    this.permissions = permissions;
}


public String getModuleName(){
    return moduleName;
}


public void setModuleName(String moduleName){
    this.moduleName = moduleName;
}


}