package com.cym.model;
 import cn.craccd.sqlHelper.bean.BaseModel;
import cn.craccd.sqlHelper.config.SingleIndex;
import cn.craccd.sqlHelper.config.Table;
@Table
public class Credit extends BaseModel{

@SingleIndex(unique = true)
 private String key;

 private String adminId;


public String getKey(){
    return key;
}


public void setAdminId(String adminId){
    this.adminId = adminId;
}


public String getAdminId(){
    return adminId;
}


public void setKey(String key){
    this.key = key;
}


}