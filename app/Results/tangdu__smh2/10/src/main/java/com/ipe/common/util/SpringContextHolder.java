package com.ipe.common.util;
 import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
public class SpringContextHolder implements ApplicationContextAware,DisposableBean{

 private  ApplicationContext applicationContext;

 private  Logger logger;


public ApplicationContext getApplicationContext(){
    return applicationContext;
}


@Override
public void setApplicationContext(ApplicationContext applicationContext){
    logger.debug("注入ApplicationContext到SpringContextHolder:{}", applicationContext);
    if (SpringContextHolder.applicationContext != null) {
        logger.warn("SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:" + SpringContextHolder.applicationContext);
    }
    // NOSONAR
    SpringContextHolder.applicationContext = applicationContext;
}


public void clearHolder(){
    logger.debug("清除SpringContextHolder中的ApplicationContext:" + applicationContext);
    applicationContext = null;
}


@Override
public void destroy(){
    SpringContextHolder.clearHolder();
}


public T getBean(Class<T> requiredType){
    return applicationContext.getBean(requiredType);
}


}