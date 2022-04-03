package com.cym.utils;
 import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
@Component
@Lazy(false)
public class ApplicationContextRegister implements ApplicationContextAware{

 private  ApplicationContext APPLICATION_CONTEXT;


@Override
public void setApplicationContext(ApplicationContext applicationContext){
    APPLICATION_CONTEXT = applicationContext;
}


public ApplicationContext getApplicationContext(){
    return APPLICATION_CONTEXT;
}


}