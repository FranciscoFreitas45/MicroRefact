package org.jeecgframework.core.util;
 import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
public class ApplicationContextUtil implements ApplicationContextAware{

 private  ApplicationContext context;


public void setApplicationContext(ApplicationContext context){
    this.context = context;
}


public ApplicationContext getContext(){
    return context;
}


}