package com.cym.model;
 import cn.craccd.sqlHelper.bean.BaseModel;
import cn.craccd.sqlHelper.config.SingleIndex;
import cn.craccd.sqlHelper.config.Table;
@Table
public class Www extends BaseModel{

@SingleIndex(unique = true)
 private String dir;


public void setDir(String dir){
    this.dir = dir;
}


public String getDir(){
    return dir;
}


}