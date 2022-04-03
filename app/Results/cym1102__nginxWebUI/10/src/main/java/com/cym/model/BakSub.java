package com.cym.model;
 import cn.craccd.sqlHelper.bean.BaseModel;
import cn.craccd.sqlHelper.config.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("备份子文件")
@Table
public class BakSub extends BaseModel{

@ApiModelProperty("主文件id")
 private String bakId;

@ApiModelProperty("名称")
 private String name;

@ApiModelProperty("内容")
 private String content;


public void setName(String name){
    this.name = name;
}


public String getBakId(){
    return bakId;
}


public void setContent(String content){
    this.content = content;
}


public String getName(){
    return name;
}


public String getContent(){
    return content;
}


public void setBakId(String bakId){
    this.bakId = bakId;
}


}