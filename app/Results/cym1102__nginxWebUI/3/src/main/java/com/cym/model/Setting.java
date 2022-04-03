package com.cym.model;
 import cn.craccd.sqlHelper.bean.BaseModel;
import cn.craccd.sqlHelper.config.CompositeIndex;
import cn.craccd.sqlHelper.config.SingleIndex;
import cn.craccd.sqlHelper.config.Table;
@Table
@CompositeIndex(colums = { "key", "value" }, unique = true)
public class Setting extends BaseModel{

@SingleIndex(unique = true)
 private String key;

 private String value;


public String getKey(){
    return key;
}


public String getValue(){
    return value;
}


public void setValue(String value){
    this.value = value;
}


public void setKey(String key){
    this.key = key;
}


}