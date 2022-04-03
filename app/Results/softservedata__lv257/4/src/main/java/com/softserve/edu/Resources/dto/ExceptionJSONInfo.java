package com.softserve.edu.Resources.dto;
 public class ExceptionJSONInfo {

 private  String url;

 private  String message;


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