package org.jugbd.mnet.web;
 import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
@Component("applicationContextProvider")
public class ApplicationContextProvider implements ApplicationContextAware{

 private  ApplicationContext context;


public ApplicationContext getApplicationContext(){
    return context;
}


@Override
public void setApplicationContext(ApplicationContext applicationContext){
    context = applicationContext;
}


}