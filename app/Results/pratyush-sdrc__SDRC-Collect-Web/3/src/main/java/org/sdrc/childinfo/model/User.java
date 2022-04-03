package org.sdrc.childinfo.model;
 import java.util.Date;
import java.util.List;
public class User {

 private  int userId;

 private  String lastUpdatedBy;

 private  Date lastUpdatedDate;

 private  String userContactNo;

 private  String userEmailId;

 private  String userName;

 private  boolean active;

 private  List<UserRoleSchemeMapping> userRoleSchemeMappings;


public String getUserEmailId(){
    return userEmailId;
}


public void setUserRoleSchemeMappings(List<UserRoleSchemeMapping> userRoleSchemeMappings){
    this.userRoleSchemeMappings = userRoleSchemeMappings;
}


public void setLastUpdatedDate(Date lastUpdatedDate){
    this.lastUpdatedDate = lastUpdatedDate;
}


public List<UserRoleSchemeMapping> getUserRoleSchemeMappings(){
    return userRoleSchemeMappings;
}


public void setLastUpdatedBy(String lastUpdatedBy){
    this.lastUpdatedBy = lastUpdatedBy;
}


public boolean isActive(){
    return active;
}


public String getUserContactNo(){
    return userContactNo;
}


public void setUserEmailId(String userEmailId){
    this.userEmailId = userEmailId;
}


public void setUserContactNo(String userContactNo){
    this.userContactNo = userContactNo;
}


public void setActive(boolean active){
    this.active = active;
}


public void setUserName(String userName){
    this.userName = userName;
}


public String getUserName(){
    return userName;
}


public String getLastUpdatedBy(){
    return lastUpdatedBy;
}


public int getUserId(){
    return userId;
}


public void setUserId(int userId){
    this.userId = userId;
}


public Date getLastUpdatedDate(){
    return lastUpdatedDate;
}


}