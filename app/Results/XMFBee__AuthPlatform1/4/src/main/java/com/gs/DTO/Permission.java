package com.gs.DTO;
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


public void setPermissionId(String permissionId){
    this.permissionId = permissionId;
}


public String getPermissionDes(){
    return permissionDes;
}


public String getModuleId(){
    return moduleId;
}


public String getStatus(){
    return status;
}


public String getPermissionName(){
    return permissionName;
}


public void setPermissionName(String permissionName){
    this.permissionName = permissionName;
}


public void setModule(Module module){
    this.module = module;
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