package com.softserve.edu.Resources.dto;
 public class FieldErrorDTO {

 private  String field;

 private  String message;

public FieldErrorDTO(String field, String message) {
    this.field = field;
    this.message = message;
}
public void setField(String field){
    this.field = field;
}


public String getField(){
    return field;
}


public String getMessage(){
    return message;
}


public void setMessage(String message){
    this.message = message;
}


}