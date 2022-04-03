package edu.xr.campusweibo.web.rest.errors;
 import java.io.Serializable;
public class ParameterizedErrorVM implements Serializable{

 private  long serialVersionUID;

 private  String message;

 private  String[] params;

public ParameterizedErrorVM(String message, String... params) {
    this.message = message;
    this.params = params;
}
public String getMessage(){
    return message;
}


public String[] getParams(){
    return params;
}


}