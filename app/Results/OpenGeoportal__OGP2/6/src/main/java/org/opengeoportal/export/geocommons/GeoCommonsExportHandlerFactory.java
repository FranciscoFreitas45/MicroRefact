package org.opengeoportal.export.geocommons;
 import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
public class GeoCommonsExportHandlerFactory implements ApplicationContextAware{

 private  ApplicationContext applicationContext;

 final  Logger logger;


public GeoCommonsExportHandler getObject(){
    logger.info("Creating GeoCommonsExportHandler bean");
    return applicationContext.getBean(GeoCommonsExportHandler.class);
}


@Override
public void setApplicationContext(ApplicationContext appContext){
    applicationContext = appContext;
}


public Class<GeoCommonsExportHandler> getObjectType(){
    return GeoCommonsExportHandler.class;
}


public boolean isSingleton(){
    return false;
}


}