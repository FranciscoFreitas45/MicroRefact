package com.cym.DTO;
 import cn.craccd.sqlHelper.bean.BaseModel;
import cn.craccd.sqlHelper.config.CompositeIndex;
import cn.craccd.sqlHelper.config.InitValue;
import cn.craccd.sqlHelper.config.SingleIndex;
import cn.craccd.sqlHelper.config.Table;
public class Admin extends BaseModel{

 private String name;

 private String pass;

 private String key;

 private Boolean auth;

 private Boolean api;

 private String token;

 private Integer type;


public String getKey(){
    return key;
}


public Boolean getAuth(){
    return auth;
}


public String getName(){
    return name;
}


public String getPass(){
    return pass;
}


public Boolean getApi(){
    return api;
}


public Integer getType(){
    return type;
}


public String getToken(){
    return token;
}


}