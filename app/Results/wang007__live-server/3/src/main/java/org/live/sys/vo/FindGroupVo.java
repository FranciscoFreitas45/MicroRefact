package org.live.sys.vo;
 public class FindGroupVo {

 private  String id;

 private  String groupName;

 private  Integer serialNo;

 private  String description;

 private  int isEnable;

 private  String[] roleNames;


public String[] getRoleNames(){
    return roleNames;
}


public void setRoleNames(String[] roleNames){
    this.roleNames = roleNames;
}


public String getGroupName(){
    return groupName;
}


public void setGroupName(String groupName){
    this.groupName = groupName;
}


public void setId(String id){
    this.id = id;
}


public String getId(){
    return id;
}


public void setSerialNo(Integer serialNo){
    this.serialNo = serialNo;
}


public void setDescription(String description){
    this.description = description;
}


public void setIsEnable(int isEnable){
    this.isEnable = isEnable;
}


public String getDescription(){
    return description;
}


public Integer getSerialNo(){
    return serialNo;
}


public int getIsEnable(){
    return isEnable;
}


}