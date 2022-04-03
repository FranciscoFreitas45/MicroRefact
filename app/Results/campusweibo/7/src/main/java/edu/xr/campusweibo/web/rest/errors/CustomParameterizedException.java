package edu.xr.campusweibo.web.rest.errors;
 public class CustomParameterizedException extends RuntimeException{

 private  long serialVersionUID;

 private  String message;

 private  String[] params;


public ParameterizedErrorVM getErrorVM(){
    return new ParameterizedErrorVM(message, params);
}


}