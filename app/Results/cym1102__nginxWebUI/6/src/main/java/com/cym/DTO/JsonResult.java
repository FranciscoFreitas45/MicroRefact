package com.cym.DTO;
 import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
public class JsonResult {

 private  boolean success;

 private  String status;

 private  String msg;

 private  T obj;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


public String getMsg(){
    return msg;
}


public T getObj(){
    return obj;
}


public String getStatus(){
    return status;
}


public boolean isSuccess(){
    return success;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isSuccess"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public void setSuccess(boolean success){
    this.success = success;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSuccess"))

.queryParam("success",success)
;
restTemplate.put(builder.toUriString(),null);
}


public void setStatus(String status){
    this.status = status;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setStatus"))

.queryParam("status",status)
;
restTemplate.put(builder.toUriString(),null);
}


public void setObj(T obj){
    this.obj = obj;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setObj"))

.queryParam("obj",obj)
;
restTemplate.put(builder.toUriString(),null);
}


public void setMsg(String msg){
    this.msg = msg;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setMsg"))

.queryParam("msg",msg)
;
restTemplate.put(builder.toUriString(),null);
}


}