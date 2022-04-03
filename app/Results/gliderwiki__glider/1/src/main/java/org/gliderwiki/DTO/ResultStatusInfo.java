package org.gliderwiki.DTO;
 import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
public class ResultStatusInfo implements Serializable{

 private  long serialVersionUID;

 private  String message;

 private  String redirectUrl;

 private  String errorMsg;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://12";


public String getMessage(){
    return message;
}


public String getRedirectUrl(){
    return redirectUrl;
}


public String getErrorMsg(){
    return errorMsg;
}


public void setErrorMsg(String errorMsg){
    this.errorMsg = errorMsg;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setErrorMsg"))

.queryParam("errorMsg",errorMsg)
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


public void setRedirectUrl(String redirectUrl){
    this.redirectUrl = redirectUrl;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setRedirectUrl"))

.queryParam("redirectUrl",redirectUrl)
;
restTemplate.put(builder.toUriString(),null);
}


}