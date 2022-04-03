package com.designpattern.chainOfResponsibility.duplicate;
 public class AbstractHandler {

 private  Handler handler;


public void setHandler(Handler handler){
    this.handler = handler;
}


public Handler getHandler(){
    return handler;
}


}