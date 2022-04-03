package com.cg.sprint.exceptions;
 public class ErrorInfo {

 private  String url;

 private  String message;

public ErrorInfo(String url, String message) {
    this.url = url;
    this.message = message;
}
public String getUrl(){
    return url;
}


public String getMessage(){
    return message;
}


public void setMessage(String message){
    this.message = message;
}


public void setUrl(String url){
    this.url = url;
}


}