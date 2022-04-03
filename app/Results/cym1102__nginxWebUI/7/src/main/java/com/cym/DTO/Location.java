package com.cym.DTO;
 import cn.craccd.sqlHelper.bean.BaseModel;
import cn.craccd.sqlHelper.config.InitValue;
import cn.craccd.sqlHelper.config.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
public class Location extends BaseModel{

 private String serverId;

 private String path;

 private Integer type;

 private String locationParamJson;

 private String value;

 private String upstreamType;

 private String upstreamId;

 private String upstreamPath;

 private String rootPath;

 private String rootPage;

 private String rootType;

 private Integer header;

 private Integer websocket;


public void setServerId(String serverId){
    this.serverId = serverId;
}


public void setRootPage(String rootPage){
    this.rootPage = rootPage;
}


public String getUpstreamId(){
    return upstreamId;
}


public void setHeader(Integer header){
    this.header = header;
}


public Integer getHeader(){
    return header;
}


public String getUpstreamType(){
    return upstreamType;
}


public void setWebsocket(Integer websocket){
    this.websocket = websocket;
}


public String getPath(){
    return path;
}


public String getRootType(){
    return rootType;
}


public void setRootType(String rootType){
    this.rootType = rootType;
}


public String getRootPage(){
    return rootPage;
}


public void setUpstreamPath(String upstreamPath){
    this.upstreamPath = upstreamPath;
}


public void setPath(String path){
    this.path = path;
}


public void setType(Integer type){
    this.type = type;
}


public String getUpstreamPath(){
    return upstreamPath;
}


public Integer getWebsocket(){
    return websocket;
}


public void setLocationParamJson(String locationParamJson){
    this.locationParamJson = locationParamJson;
}


public String getValue(){
    return value;
}


public String getLocationParamJson(){
    return locationParamJson;
}


public void setUpstreamType(String upstreamType){
    this.upstreamType = upstreamType;
}


public Integer getType(){
    return type;
}


public void setRootPath(String rootPath){
    this.rootPath = rootPath;
}


public void setValue(String value){
    this.value = value;
}


public String getServerId(){
    return serverId;
}


public String getRootPath(){
    return rootPath;
}


public void setUpstreamId(String upstreamId){
    this.upstreamId = upstreamId;
}


}