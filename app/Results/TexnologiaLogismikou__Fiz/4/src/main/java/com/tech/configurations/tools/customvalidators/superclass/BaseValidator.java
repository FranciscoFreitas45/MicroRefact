package com.tech.configurations.tools.customvalidators.superclass;
 import com.tech.configurations.tools.Responses;
import com.tech.configurations.tools.customvalidators.interfaces.ICustomValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
public class BaseValidator implements ICustomValidator{

 private  ResponseEntity RESPONSE_ERROR;

 private  ResponseEntity RESPONSE_DONE;

 private  String name;

protected BaseValidator(ResponseEntity RESPONSE_ERROR, String name) {
    this.name = name;
    this.RESPONSE_ERROR = RESPONSE_ERROR;
}
public String getName(){
    return name;
}


public ResponseEntity getErrorResponse(){
    return RESPONSE_ERROR;
}


public ResponseEntity getSuccessResponse(){
    return RESPONSE_DONE;
}


}