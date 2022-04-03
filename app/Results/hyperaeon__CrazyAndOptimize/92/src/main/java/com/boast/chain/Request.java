package com.boast.chain;
 public class Request {

 private  String requestType;

 private  String requestContent;

 private  int number;

public Request() {
}public Request(String requestType, String requestContent, int number) {
    this.requestType = requestType;
    this.requestContent = requestContent;
    this.number = number;
}
public String getRequestType(){
    return requestType;
}


public int getNumber(){
    return number;
}


public String getRequestContent(){
    return requestContent;
}


public void setRequestContent(String requestContent){
    this.requestContent = requestContent;
}


public void setNumber(int number){
    this.number = number;
}


public void setRequestType(String requestType){
    this.requestType = requestType;
}


}