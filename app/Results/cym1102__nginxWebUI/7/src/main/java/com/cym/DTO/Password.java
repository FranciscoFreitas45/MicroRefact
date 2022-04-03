package com.cym.DTO;
 import cn.craccd.sqlHelper.bean.BaseModel;
import cn.craccd.sqlHelper.config.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
public class Password extends BaseModel{

 private String name;

 private String pass;

 private String path;

 private String descr;

 private String pathStr;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


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


public void setPathStr(String pathStr){
    this.pathStr = pathStr;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPathStr"))

.queryParam("pathStr",pathStr)
;
restTemplate.put(builder.toUriString(),null);
}


}