package com.cym.model;
 import cn.craccd.sqlHelper.bean.BaseModel;
import cn.craccd.sqlHelper.config.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("Http参数")
@Table
public class Http extends BaseModel{

@ApiModelProperty("参数名")
 private String name;

@ApiModelProperty("参数值")
 private String value;

@ApiModelProperty("参数单位")
 private String unit;

@ApiModelProperty(hidden = true)
 private Long seq;

public Http() {
}public Http(String name, String value, Long seq) {
    this.name = name;
    this.value = value;
    this.seq = seq;
}
public void setName(String name){
    this.name = name;
}


public Long getSeq(){
    return seq;
}


public String getValue(){
    return value;
}


public String getName(){
    return name;
}


public void setUnit(String unit){
    this.unit = unit;
}


public void setValue(String value){
    this.value = value;
}


public String getUnit(){
    return unit;
}


public void setSeq(Long seq){
    this.seq = seq;
}


}