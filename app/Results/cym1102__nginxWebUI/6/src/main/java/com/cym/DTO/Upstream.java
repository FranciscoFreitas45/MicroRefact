package com.cym.DTO;
 import cn.craccd.sqlHelper.bean.BaseModel;
import cn.craccd.sqlHelper.config.InitValue;
import cn.craccd.sqlHelper.config.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
public class Upstream extends BaseModel{

 private String name;

 private String tactics;

 private Integer proxyType;

 private Integer monitor;

 private Long seq;


public Long getSeq(){
    return seq;
}


public String getName(){
    return name;
}


public Integer getMonitor(){
    return monitor;
}


public String getTactics(){
    return tactics;
}


public Integer getProxyType(){
    return proxyType;
}


}