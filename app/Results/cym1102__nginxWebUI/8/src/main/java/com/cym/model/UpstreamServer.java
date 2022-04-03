package com.cym.model;
 import cn.craccd.sqlHelper.bean.BaseModel;
import cn.craccd.sqlHelper.config.InitValue;
import cn.craccd.sqlHelper.config.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("负载节点server")
@Table
public class UpstreamServer extends BaseModel{

@ApiModelProperty("*负载均衡upstream的id")
 private String upstreamId;

@ApiModelProperty("*负载节点ip (例:10.10.10.1)")
 private String server;

@ApiModelProperty("*负载节点端口 (例:8080)")
 private Integer port;

@ApiModelProperty("负载节点权重 (默认:1)")
@InitValue("1")
 private Integer weight;

@ApiModelProperty("失败等待时间,秒 (默认:10)")
@InitValue("10")
 private Integer failTimeout;

@ApiModelProperty("最大失败次数 (默认:1)")
@InitValue("1")
 private Integer maxFails;

@ApiModelProperty("状态策略 'none':无(默认) 'down':停用 'backup':备用")
@InitValue("none")
 private String status;

@ApiModelProperty(hidden = true, name = "监控状态 -1:未检测(默认) 0:不通 1:通")
@InitValue("-1")
 private Integer monitorStatus;


public String getServer(){
    return server;
}


public void setMonitorStatus(Integer monitorStatus){
    this.monitorStatus = monitorStatus;
}


public Integer getMaxFails(){
    return maxFails;
}


public Integer getMonitorStatus(){
    return monitorStatus;
}


public void setFailTimeout(Integer failTimeout){
    this.failTimeout = failTimeout;
}


public Integer getWeight(){
    return weight;
}


public void setWeight(Integer weight){
    this.weight = weight;
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


public void setPort(Integer port){
    this.port = port;
}


public String getUpstreamId(){
    return upstreamId;
}


public void setStatus(String status){
    this.status = status;
}


public void setMaxFails(Integer maxFails){
    this.maxFails = maxFails;
}


public Integer getPort(){
    return port;
}


public void setUpstreamId(String upstreamId){
    this.upstreamId = upstreamId;
}


}