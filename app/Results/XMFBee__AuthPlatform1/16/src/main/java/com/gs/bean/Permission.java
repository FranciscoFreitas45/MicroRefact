package com.gs.bean;
 public class Permission {

 private  String permissionId;

 private  String permissionName;

 private  String permissionZhname;

 private  String permissionDes;

 private  String moduleId;

 private  String permissionStatus;

 private  String status;

 public  Module module;


public Module getModule(){
    return module;
}


public String getPermissionStatus(){
    return permissionStatus;
}


public void setModuleId(String moduleId){
    this.moduleId = moduleId;
}


public void setPermissionId(String permissionId){
    this.permissionId = permissionId;
}


public String getPermissionDes(){
    return permissionDes;
}


public String getModuleId(){
    return moduleId;
}


public void setPermissionStatus(String permissionStatus){
    this.permissionStatus = permissionStatus;
}


public String getStatus(){
    return status;
}


public String getPermissionName(){
    return permissionName;
}


public void setStatus(String status){
    this.status = status;
}


public void setPermissionName(String permissionName){
    this.permissionName = permissionName;
}


public void setPermissionZhname(String permissionZhname){
    this.permissionZhname = permissionZhname;
}


public void setModule(Module module){
    this.module = module;
}


@Override
public String toString(){
    return "Permission{" + "permissionId='" + permissionId + '\'' + ", permissionName='" + permissionName + '\'' + ", permissionZhname='" + permissionZhname + '\'' + ", permissionDes='" + permissionDes + '\'' + ", permissionStatus='" + permissionStatus + '\'' + ", status='" + status + '\'' + '}';
}


public void setPermissionDes(String permissionDes){
    this.permissionDes = permissionDes;
}


public String getPermissionId(){
    return permissionId;
}


public String getPermissionZhname(){
    return permissionZhname;
}


}