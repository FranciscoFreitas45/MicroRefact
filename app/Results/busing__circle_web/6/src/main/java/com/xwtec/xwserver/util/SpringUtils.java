package com.xwtec.xwserver.util;
 import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
@SuppressWarnings("unchecked")
@Component
public class SpringUtils implements ApplicationContextAware{

 private  ApplicationContext applicationContext;


public Map<String,T> getBeansOfType(Class<T> baseType){
    return applicationContext.getBeansOfType(baseType);
}


public void setApplicationContext(ApplicationContext applicationContext){
    SpringUtils.applicationContext = applicationContext;
}


public T getBean(String beanName){
    return (T) applicationContext.getBean(beanName);
}


}