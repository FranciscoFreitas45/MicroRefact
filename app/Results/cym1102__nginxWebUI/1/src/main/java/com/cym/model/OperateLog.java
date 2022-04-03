package com.cym.model;
 import cn.craccd.sqlHelper.bean.BaseModel;
import cn.craccd.sqlHelper.config.Table;
@Table
public class OperateLog extends BaseModel{

 private String adminName;

 private String beforeConf;

 private String afterConf;


public void setBeforeConf(String beforeConf){
    this.beforeConf = beforeConf;
}


public void setAdminName(String adminName){
    this.adminName = adminName;
}


public void setAfterConf(String afterConf){
    this.afterConf = afterConf;
}


public String getBeforeConf(){
    return beforeConf;
}


public String getAdminName(){
    return adminName;
}


public String getAfterConf(){
    return afterConf;
}


}