package org.opengeoportal.proxy;
 import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
public class ImageDownloaderFactory implements ApplicationContextAware{

 private  ApplicationContext applicationContext;

 final  Logger logger;


public ImageDownloader getObject(){
    logger.info("Creating ImageDownloader bean");
    return applicationContext.getBean(ImageDownloader.class);
}


@Override
public void setApplicationContext(ApplicationContext appContext){
    applicationContext = appContext;
}


public Class<ImageDownloader> getObjectType(){
    return ImageDownloader.class;
}


public boolean isSingleton(){
    return false;
}


}