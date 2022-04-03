package io.swagger.api;
 @javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-20T13:31:05.475Z")
public class ApiException extends Exception{

 private  int code;

public ApiException(int code, String msg) {
    super(msg);
    this.code = code;
}
}