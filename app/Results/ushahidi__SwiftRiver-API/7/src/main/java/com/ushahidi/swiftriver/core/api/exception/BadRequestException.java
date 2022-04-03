package com.ushahidi.swiftriver.core.api.exception;
 import java.util.List;
public class BadRequestException extends RuntimeException{

 private  long serialVersionUID;

 private  List<ErrorField> errors;

public BadRequestException(String message) {
    super(message);
}public BadRequestException() {
    super();
}public BadRequestException(String message, Throwable cause) {
    super(message, cause);
}public BadRequestException(Throwable cause) {
    super(cause);
}
public void setErrors(List<ErrorField> errors){
    this.errors = errors;
}


public List<ErrorField> getErrors(){
    return errors;
}


}