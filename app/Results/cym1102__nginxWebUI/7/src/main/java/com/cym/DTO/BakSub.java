package com.cym.DTO;
 import cn.craccd.sqlHelper.bean.BaseModel;
import cn.craccd.sqlHelper.config.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
public class BakSub extends BaseModel{

 private String bakId;

 private String name;

 private String content;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://10";


public String getBakId(){
    return bakId;
}


public String getName(){
    return name;
}


public String getContent(){
    return content;
}


public void setBakId(String bakId){
    this.bakId = bakId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setBakId"))

.queryParam("bakId",bakId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setName(String name){
    this.name = name;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setName"))

.queryParam("name",name)
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