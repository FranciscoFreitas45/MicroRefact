package org.opengeoportal.config;
 import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
public class PropertiesFile {

 private Properties properties;

 private Resource resource;

 protected  Logger logger;


public void readProperties(){
    InputStream is = null;
    try {
        properties = new Properties();
        is = resource.getInputStream();
        properties.load(is);
    } finally {
        IOUtils.closeQuietly(is);
    }
}


public String getProperty(String propertyKey,String defaultValue){
    return properties.getProperty(propertyKey, defaultValue);
}


public void refreshProperties(){
    readProperties();
}


public Properties getProperties(){
    if (properties == null) {
        readProperties();
    }
    return properties;
}


public String[] getPropertyArray(String propertyName){
    return this.getProperty(propertyName).split(",");
}


public void setResource(Resource resource){
    this.resource = resource;
}


}