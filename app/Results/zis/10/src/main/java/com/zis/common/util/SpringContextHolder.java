package com.zis.common.util;
 import java.util.Map;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
public class SpringContextHolder implements ApplicationContextAware{

 private  ApplicationContext applicationContext;


public void checkApplicationContext(){
    if (applicationContext == null) {
        throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
    }
}


public void setApplicationContext(ApplicationContext applicationContext){
    SpringContextHolder.applicationContext = applicationContext;
}


public ApplicationContext getApplicationContext(){
    checkApplicationContext();
    return applicationContext;
}


@SuppressWarnings("unchecked")
public T getBean(Class<T> clazz){
    checkApplicationContext();
    @SuppressWarnings("rawtypes")
    Map beanMaps = applicationContext.getBeansOfType(clazz);
    if (beanMaps != null && !beanMaps.isEmpty()) {
        return (T) beanMaps.values().iterator().next();
    } else {
        return null;
    }
}


}