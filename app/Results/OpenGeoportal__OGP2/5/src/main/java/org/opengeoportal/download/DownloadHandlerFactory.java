package org.opengeoportal.download;
 import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
public class DownloadHandlerFactory implements ApplicationContextAware{

 private  ApplicationContext applicationContext;

 final  Logger logger;


public DownloadHandler getObject(){
    logger.info("Creating DownloadHandler bean");
    return applicationContext.getBean(DownloadHandler.class);
}


@Override
public void setApplicationContext(ApplicationContext appContext){
    applicationContext = appContext;
}


public Class<DownloadHandler> getObjectType(){
    return DownloadHandler.class;
}


public boolean isSingleton(){
    return false;
}


}