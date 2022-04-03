package com.yalcin.event;
 import org.springframework.context.ApplicationEvent;
public class OnPasswordForgotEvent extends ApplicationEvent{

 private  long serialVersionUID;

 private  String email;

public OnPasswordForgotEvent(String email) {
    super(email);
    this.email = email;
}
public void setEmail(String email){
    this.email = email;
}


public String getEmail(){
    return this.email;
}


}