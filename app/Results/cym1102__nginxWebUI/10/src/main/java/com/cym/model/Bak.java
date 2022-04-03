package com.cym.model;
 import cn.craccd.sqlHelper.bean.BaseModel;
import cn.craccd.sqlHelper.config.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("备份文件")
@Table
public class Bak extends BaseModel{

@ApiModelProperty("时间")
 private String time;

@ApiModelProperty("主文件内容")
 private String content;


public void setContent(String content){
    this.content = content;
}


public String getTime(){
    return time;
}


public String getContent(){
    return content;
}


public void setTime(String time){
    this.time = time;
}


}