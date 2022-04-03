package com.cym.DTO;
 import cn.craccd.sqlHelper.bean.BaseModel;
import cn.craccd.sqlHelper.config.InitValue;
import cn.craccd.sqlHelper.config.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
public class Stream extends BaseModel{

 private String name;

 private String value;

 private Long seq;


public void setName(String name){
    this.name = name;
}


public String getValue(){
    return value;
}


public Long getSeq(){
    return seq;
}


public String getName(){
    return name;
}


public void setValue(String value){
    this.value = value;
}


public void setSeq(Long seq){
    this.seq = seq;
}


}