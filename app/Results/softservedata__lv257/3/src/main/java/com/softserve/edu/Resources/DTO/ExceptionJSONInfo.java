package com.softserve.edu.Resources.DTO;
 public class ExceptionJSONInfo {

 private  String url;

 private  String message;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public String getUrl(){
    return url;
}


public String getMessage(){
    return message;
}


public void setUrl(String url){
    this.url = url;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUrl"))

.queryParam("url",url)
;
restTemplate.put(builder.toUriString(),null);
}


public void setMessage(String message){
    this.message = message;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setMessage"))

.queryParam("message",message)
;
restTemplate.put(builder.toUriString(),null);
}


}