package com.dtdhehe.ptulife.DTO;
 public class ResultVO {

 private  Integer SUCCESS;

 private  Integer FAILED;

 private  String error_code;

 private  String error_msg;

 private  String status;

 private  Object object;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


public String getError_msg(){
    return error_msg;
}


public Object getObject(){
    return object;
}


public String getError_code(){
    return error_code;
}


public String getStatus(){
    return status;
}


public void setStatus(String status){
    this.status = status;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setStatus"))

.queryParam("status",status)
;
restTemplate.put(builder.toUriString(),null);
}


public void setError_msg(String error_msg){
    this.error_msg = error_msg;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setError_msg"))

.queryParam("error_msg",error_msg)
;
restTemplate.put(builder.toUriString(),null);
}


public void setObject(Object object){
    this.object = object;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setObject"))

.queryParam("object",object)
;
restTemplate.put(builder.toUriString(),null);
}


}