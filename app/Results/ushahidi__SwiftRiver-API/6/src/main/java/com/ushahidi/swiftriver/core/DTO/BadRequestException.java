package com.ushahidi.swiftriver.core.DTO;
 import java.util.List;
public class BadRequestException extends RuntimeException{

 private  long serialVersionUID;

 private  List<ErrorField> errors;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";

public BadRequestException(String message) {
    super(message);
}public BadRequestException() {
    super();
}public BadRequestException(String message, Throwable cause) {
    super(message, cause);
}public BadRequestException(Throwable cause) {
    super(cause);
}
public List<ErrorField> getErrors(){
    return errors;
}


public void setErrors(List<ErrorField> errors){
    this.errors = errors;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setErrors"))

.queryParam("errors",errors)
;
restTemplate.put(builder.toUriString(),null);
}


}