package com.cym.model;
 import cn.craccd.sqlHelper.bean.BaseModel;
import cn.craccd.sqlHelper.config.CompositeIndex;
import cn.craccd.sqlHelper.config.InitValue;
import cn.craccd.sqlHelper.config.SingleIndex;
import cn.craccd.sqlHelper.config.Table;
@Table
public class Admin extends BaseModel{

@SingleIndex(unique = true)
 private String name;

 private String pass;

 private String key;

@InitValue("false")
 private Boolean auth;

@InitValue("false")
 private Boolean api;

 private String token;

@InitValue("0")
 private Integer type;


public void setName(String name){
    this.name = name;
}


public String getKey(){
    return key;
}


public void setAuth(Boolean auth){
    this.auth = auth;
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


public void setType(Integer type){
    this.type = type;
}


public void setToken(String token){
    this.token = token;
}


public Integer getType(){
    return type;
}


public String getToken(){
    return token;
}


public void setApi(Boolean api){
    this.api = api;
}


public void setPass(String pass){
    this.pass = pass;
}


public void setKey(String key){
    this.key = key;
}


}