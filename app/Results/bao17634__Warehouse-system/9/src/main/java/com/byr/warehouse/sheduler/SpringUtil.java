package com.byr.warehouse.sheduler;
 import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
@Component
public class SpringUtil implements ApplicationContextAware{

 private  Logger logger;

 private  ApplicationContext applicationContext;


@Override
public void setApplicationContext(ApplicationContext applicationContext){
    if (SpringUtil.applicationContext == null) {
        SpringUtil.applicationContext = applicationContext;
    }
    logger.info("========ApplicationContext配置成功,在普通类可以通过调用SpringUtils.getApplicationContext()获取applicationContext对象,applicationContext=" + SpringUtil.applicationContext + "========");
}


public ApplicationContext getApplicationContext(){
    return applicationContext;
}


public T getBean(String name,Class<T> clazz){
    return getApplicationContext().getBean(name, clazz);
}


}