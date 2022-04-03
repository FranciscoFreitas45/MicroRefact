package org.sdrc.childinfo.model;
 import java.sql.Timestamp;
import java.util.List;
public class Permission {

 private  int permissionId;

 private  String description;

 private  String lastUpdatedBy;

 private  Timestamp lastUpdatedDate;

 private  String permissionCode;

 private  String permissionName;

 private  List<ValueObject> featurePermissionMappings;


public List<ValueObject> getFeaturePermissionMappings(){
    return featurePermissionMappings;
}


public void setLastUpdatedDate(Timestamp lastUpdatedDate){
    this.lastUpdatedDate = lastUpdatedDate;
}


public void setLastUpdatedBy(String lastUpdatedBy){
    this.lastUpdatedBy = lastUpdatedBy;
}


public String getPermissionCode(){
    return permissionCode;
}


public void setPermissionId(int permissionId){
    this.permissionId = permissionId;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public String getPermissionName(){
    return permissionName;
}


public void setPermissionName(String permissionName){
    this.permissionName = permissionName;
}


public void setPermissionCode(String permissionCode){
    this.permissionCode = permissionCode;
}


public void setFeaturePermissionMappings(List<ValueObject> featurePermissionMappings){
    this.featurePermissionMappings = featurePermissionMappings;
}


public String getLastUpdatedBy(){
    return lastUpdatedBy;
}


public int getPermissionId(){
    return permissionId;
}


public Timestamp getLastUpdatedDate(){
    return lastUpdatedDate;
}


}