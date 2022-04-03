package com.cym.DTO;
 import cn.craccd.sqlHelper.bean.BaseModel;
import cn.craccd.sqlHelper.config.InitValue;
import cn.craccd.sqlHelper.config.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
public class UpstreamServer extends BaseModel{

 private String upstreamId;

 private String server;

 private Integer port;

 private Integer weight;

 private Integer failTimeout;

 private Integer maxFails;

 private String status;

 private Integer monitorStatus;


public String getServer(){
    return server;
}


public Integer getMaxFails(){
    return maxFails;
}


public Integer getMonitorStatus(){
    return monitorStatus;
}


public Integer getWeight(){
    return weight;
}


public void setServer(String server){
    this.server = server;
}


public Integer getFailTimeout(){
    return failTimeout;
}


public String getStatus(){
    return status;
}


public String getUpstreamId(){
    return upstreamId;
}


public void setMaxFails(Integer maxFails){
    this.maxFails = maxFails;
}


public Integer getPort(){
    return port;
}


}