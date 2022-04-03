package edu.xr.campusweibo.web.rest.errors;
 public class CustomParameterizedException extends RuntimeException{

 private  long serialVersionUID;

 private  String message;

 private  String[] params;

public CustomParameterizedException(String message, String... params) {
    super(message);
    this.message = message;
    this.params = params;
}
public ParameterizedErrorVM getErrorVM(){
    return new ParameterizedErrorVM(message, params);
}


}