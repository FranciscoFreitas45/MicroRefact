package org.sdrc.childinfo.model;
 import java.sql.Timestamp;
import java.util.List;
public class Role {

 private  int roleId;

 private  String description;

 private  String lastUpdatedBy;

 private  Timestamp lastUpdatedDate;

 private  String roleCode;

 private  String roleName;

 private  List<ValueObject> roleSchemes;


public void setRoleCode(String roleCode){
    this.roleCode = roleCode;
}


public String getRoleName(){
    return roleName;
}


public void setLastUpdatedDate(Timestamp lastUpdatedDate){
    this.lastUpdatedDate = lastUpdatedDate;
}


public void setRoleName(String roleName){
    this.roleName = roleName;
}


public int getRoleId(){
    return roleId;
}


public void setLastUpdatedBy(String lastUpdatedBy){
    this.lastUpdatedBy = lastUpdatedBy;
}


public void setRoleSchemes(List<ValueObject> roleSchemes){
    this.roleSchemes = roleSchemes;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public void setRoleId(int roleId){
    this.roleId = roleId;
}


public String getRoleCode(){
    return roleCode;
}


public String getLastUpdatedBy(){
    return lastUpdatedBy;
}


public List<ValueObject> getRoleSchemes(){
    return roleSchemes;
}


public Timestamp getLastUpdatedDate(){
    return lastUpdatedDate;
}


}