package org.opengeoportal.proxy;
 import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
public class ProxyFactory implements ApplicationContextAware{

 private  ApplicationContext applicationContext;

 final  Logger logger;


public GenericProxy getObject(){
    logger.info("Creating GenericProxy bean");
    return applicationContext.getBean(GenericProxy.class);
}


@Override
public void setApplicationContext(ApplicationContext appContext){
    applicationContext = appContext;
}


public Class<GenericProxy> getObjectType(){
    return GenericProxy.class;
}


public boolean isSingleton(){
    return false;
}


}