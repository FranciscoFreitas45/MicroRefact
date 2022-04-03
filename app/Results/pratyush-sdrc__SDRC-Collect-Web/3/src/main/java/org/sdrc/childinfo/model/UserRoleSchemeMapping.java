package org.sdrc.childinfo.model;
 import java.sql.Timestamp;
public class UserRoleSchemeMapping {

 private  int userRoleSchemeId;

 private  String lastUpdatedBy;

 private  Timestamp lastUpdatedDate;

 private  RoleScheme roleScheme;

 private  ValueObject user;


public void setRoleScheme(RoleScheme roleScheme){
    this.roleScheme = roleScheme;
}


public ValueObject getUser(){
    return user;
}


public void setLastUpdatedDate(Timestamp lastUpdatedDate){
    this.lastUpdatedDate = lastUpdatedDate;
}


public void setUserRoleSchemeId(int userRoleSchemeId){
    this.userRoleSchemeId = userRoleSchemeId;
}


public void setLastUpdatedBy(String lastUpdatedBy){
    this.lastUpdatedBy = lastUpdatedBy;
}


public int getUserRoleSchemeId(){
    return userRoleSchemeId;
}


public RoleScheme getRoleScheme(){
    return roleScheme;
}


public String getLastUpdatedBy(){
    return lastUpdatedBy;
}


public void setUser(ValueObject user){
    this.user = user;
}


public Timestamp getLastUpdatedDate(){
    return lastUpdatedDate;
}


}