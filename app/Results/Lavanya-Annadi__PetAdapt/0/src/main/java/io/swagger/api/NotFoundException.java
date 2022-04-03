package io.swagger.api;
 @javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-20T13:31:05.475Z")
public class NotFoundException extends ApiException{

 private  int code;

public NotFoundException(int code, String msg) {
    super(code, msg);
    this.code = code;
}
}