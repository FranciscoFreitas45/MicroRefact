package com.cym.model;
 import cn.craccd.sqlHelper.bean.BaseModel;
import cn.craccd.sqlHelper.config.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("密码文件")
@Table
public class Password extends BaseModel{

@ApiModelProperty("用户名")
 private String name;

@ApiModelProperty("密码")
 private String pass;

@ApiModelProperty(hidden = true, name = "文件路径")
 private String path;

@ApiModelProperty("描述")
 private String descr;

@ApiModelProperty(hidden = true, name = "文件内容")
 private String pathStr;


public void setName(String name){
    this.name = name;
}


public void setDescr(String descr){
    this.descr = descr;
}


public String getName(){
    return name;
}


public String getPass(){
    return pass;
}


public String getDescr(){
    return descr;
}


public String getPathStr(){
    return pathStr;
}


public String getPath(){
    return path;
}


public void setPath(String path){
    this.path = path;
}


public void setPathStr(String pathStr){
    this.pathStr = pathStr;
}


public void setPass(String pass){
    this.pass = pass;
}


}