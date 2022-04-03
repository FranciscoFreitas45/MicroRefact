package org.gliderwiki.DTO;
 import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
public class JsonResponse implements Serializable{

 private  long serialVersionUID;

 private  ResponseStatus status;

 private  ResultStatusInfo response;

 private  Object result;

 private  long serialVersionUID;

 private  String message;

 private  String redirectUrl;

 private  String errorMsg;

 private  String description;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://12";


public ResultStatusInfo getResponse(){
    return response;
}


public String getMessage(){
    return message;
}


public ResponseStatus getStatus(){
    return status;
}


public String getDescription(){
    return description;
}


public Object getResult(){
    return result;
}


public String getRedirectUrl(){
    return redirectUrl;
}


public String getErrorMsg(){
    return errorMsg;
}


public void setResult(Object result){
    this.result = result;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setResult"))

.queryParam("result",result)
;
restTemplate.put(builder.toUriString(),null);
}


public void setStatus(ResponseStatus status){
    this.status = status;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setStatus"))

.queryParam("status",status)
;
restTemplate.put(builder.toUriString(),null);
}


public String toString(){
    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toString"))

;
String aux = restTemplate.getForObject(builder.toUriString(),String.class);
return aux;
}


}