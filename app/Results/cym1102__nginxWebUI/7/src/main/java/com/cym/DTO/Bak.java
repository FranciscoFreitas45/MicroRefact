package com.cym.DTO;
 import cn.craccd.sqlHelper.bean.BaseModel;
import cn.craccd.sqlHelper.config.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
public class Bak extends BaseModel{

 private String time;

 private String content;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://10";


public String getTime(){
    return time;
}


public String getContent(){
    return content;
}


public void setTime(String time){
    this.time = time;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTime"))

.queryParam("time",time)
;
restTemplate.put(builder.toUriString(),null);
}


public void setContent(String content){
    this.content = content;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setContent"))

.queryParam("content",content)
;
restTemplate.put(builder.toUriString(),null);
}


}