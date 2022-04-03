package com.cym.DTO;
 import cn.craccd.sqlHelper.bean.BaseModel;
import cn.craccd.sqlHelper.config.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
public class Basic extends BaseModel{

 private String name;

 private String value;

 private Long seq;

public Basic() {
}public Basic(String name, String value, Long seq) {
    this.name = name;
    this.value = value;
    this.seq = seq;
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


}