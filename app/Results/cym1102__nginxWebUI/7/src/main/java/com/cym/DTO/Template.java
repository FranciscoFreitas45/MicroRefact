package com.cym.DTO;
 import cn.craccd.sqlHelper.bean.BaseModel;
import cn.craccd.sqlHelper.config.Table;
public class Template extends BaseModel{

 private String name;

 private String def;


public void setName(String name){
    this.name = name;
}


public void setDef(String def){
    this.def = def;
}


public String getName(){
    return name;
}


public String getDef(){
    return def;
}


}