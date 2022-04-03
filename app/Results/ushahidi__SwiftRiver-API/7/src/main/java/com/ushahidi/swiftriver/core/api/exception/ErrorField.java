package com.ushahidi.swiftriver.core.api.exception;
 public class ErrorField {

 private  String field;

 private  String code;

public ErrorField() {
}public ErrorField(String field, String code) {
    super();
    this.field = field;
    this.code = code;
}
public void setField(String field){
    this.field = field;
}


public void setCode(String code){
    this.code = code;
}


public String getField(){
    return field;
}


public String getCode(){
    return code;
}


}