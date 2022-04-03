package com.yalcin.event;
 import com.yalcin.entity.User;
import org.springframework.context.ApplicationEvent;
import com.yalcin.Interface.User;
public class OnRegistrationSuccessEvent extends ApplicationEvent{

 private  long serialVersionUID;

 private  String appUrl;

 private  User user;

public OnRegistrationSuccessEvent(User user, String appUrl) {
    super(user);
    this.user = user;
    this.appUrl = appUrl;
}
public void setAppUrl(String appUrl){
    this.appUrl = appUrl;
}


public User getUser(){
    return user;
}


public void setUser(User user){
    this.user = user;
}


public String getAppUrl(){
    return appUrl;
}


}