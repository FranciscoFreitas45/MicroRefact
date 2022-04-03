package com.ushahidi.swiftriver.core.DTO;
 import java.util.List;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.ushahidi.swiftriver.core.api.exception.ErrorField;
public class ErrorDTO {

 private  String message;

 private  List<ErrorField> errors;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";


public String getMessage(){
    return message;
}


public List<ErrorField> getErrors(){
    return errors;
}


public void setMessage(String message){
    this.message = message;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setMessage"))

.queryParam("message",message)
;
restTemplate.put(builder.toUriString(),null);
}


public void setErrors(List<ErrorField> errors){
    this.errors = errors;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setErrors"))

.queryParam("errors",errors)
;
restTemplate.put(builder.toUriString(),null);
}


}