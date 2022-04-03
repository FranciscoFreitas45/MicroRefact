package com.softserve.edu.Resources.registration;
 import com.softserve.edu.Resources.entity.User;
import org.springframework.context.ApplicationEvent;
import java.util.Locale;
import com.softserve.edu.Resources.Interface.User;
public class OnRegistrationCompleteEvent extends ApplicationEvent{

 private  String appUrl;

 private  Locale locale;

 private  User user;

public OnRegistrationCompleteEvent(final User user, final Locale locale, final String appUrl) {
    super(user);
    this.user = user;
    this.locale = locale;
    this.appUrl = appUrl;
}
public User getUser(){
    return user;
}


public Locale getLocale(){
    return locale;
}


public String getAppUrl(){
    return appUrl;
}


}