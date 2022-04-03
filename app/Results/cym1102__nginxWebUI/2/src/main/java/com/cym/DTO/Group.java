package com.cym.DTO;
 import cn.craccd.sqlHelper.bean.BaseModel;
import cn.craccd.sqlHelper.config.Table;
public class Group extends BaseModel{

 private String parentId;

 private String name;


public String getName(){
    return name;
}


public String getParentId(){
    return parentId;
}


}