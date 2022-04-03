package com.cym.model;
 import cn.craccd.sqlHelper.bean.BaseModel;
import cn.craccd.sqlHelper.config.Table;
@Table
public class AdminGroup extends BaseModel{

 private String adminId;

 private String groupId;


public void setGroupId(String groupId){
    this.groupId = groupId;
}


public void setAdminId(String adminId){
    this.adminId = adminId;
}


public String getAdminId(){
    return adminId;
}


public String getGroupId(){
    return groupId;
}


}