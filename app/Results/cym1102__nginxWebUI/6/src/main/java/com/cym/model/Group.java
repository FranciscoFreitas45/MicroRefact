package com.cym.model;
 import cn.craccd.sqlHelper.bean.BaseModel;
import cn.craccd.sqlHelper.config.Table;
@Table
public class Group extends BaseModel{

 private String parentId;

 private String name;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setParentId(String parentId){
    this.parentId = parentId;
}


public String getParentId(){
    return parentId;
}


}