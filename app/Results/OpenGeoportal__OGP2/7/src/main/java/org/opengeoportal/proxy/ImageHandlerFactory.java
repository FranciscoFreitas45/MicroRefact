package org.opengeoportal.proxy;
 import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
public class ImageHandlerFactory implements ApplicationContextAware{

 private  ApplicationContext applicationContext;

 final  Logger logger;


public ImageHandler getObject(){
    logger.info("Creating ImageHandler bean");
    return applicationContext.getBean(ImageHandler.class);
}


@Override
public void setApplicationContext(ApplicationContext appContext){
    applicationContext = appContext;
}


public Class<ImageHandler> getObjectType(){
    return ImageHandler.class;
}


public boolean isSingleton(){
    return false;
}


}