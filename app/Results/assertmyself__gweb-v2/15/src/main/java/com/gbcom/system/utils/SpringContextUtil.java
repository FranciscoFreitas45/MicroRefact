package com.gbcom.system.utils;
 import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
public class SpringContextUtil implements ApplicationContextAware{

 private  Log log;

 private  ApplicationContext applicationContext;


public String[] getAliases(String name){
    return applicationContext.getAliases(name);
}


public void setApplicationContext(ApplicationContext applicationContext){
    SpringContextUtil.applicationContext = applicationContext;
}


public ApplicationContext getApplicationContext(){
    return applicationContext;
}


public Class getType(String name){
    return applicationContext.getType(name);
}


public boolean containsBean(String name){
    return applicationContext.containsBean(name);
}


public boolean isSingleton(String name){
    return applicationContext.isSingleton(name);
}


public Object getBean(String name,Class requiredType){
    return applicationContext.getBean(name, requiredType);
}


}