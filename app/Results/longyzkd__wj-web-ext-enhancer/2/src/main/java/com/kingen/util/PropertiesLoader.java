package com.kingen.util;
 import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.apache.commons.io.IOUtils;
public class PropertiesLoader {

 private  Logger logger;

 private  ResourceLoader resourceLoader;

 private  Properties properties;

public PropertiesLoader(String... resourcesPaths) {
    properties = loadProperties(resourcesPaths);
}
public String getValue(String key){
    String systemProperty = System.getProperty(key);
    if (systemProperty != null) {
        return systemProperty;
    }
    if (properties.containsKey(key)) {
        return properties.getProperty(key);
    }
    return "";
}


public String getProperty(String key,String defaultValue){
    String value = getValue(key);
    return value != null ? value : defaultValue;
}


public Properties getProperties(){
    return properties;
}


public Double getDouble(String key,Integer defaultValue){
    String value = getValue(key);
    return value != null ? Double.valueOf(value) : defaultValue;
}


public Integer getInteger(String key,Integer defaultValue){
    String value = getValue(key);
    return value != null ? Integer.valueOf(value) : defaultValue;
}


public Boolean getBoolean(String key,boolean defaultValue){
    String value = getValue(key);
    return value != null ? Boolean.valueOf(value) : defaultValue;
}


public Properties loadProperties(String resourcesPaths){
    Properties props = new Properties();
    for (String location : resourcesPaths) {
        // logger.debug("Loading properties file from:" + location);
        InputStream is = null;
        try {
            Resource resource = resourceLoader.getResource(location);
            is = resource.getInputStream();
            props.load(is);
        } catch (IOException ex) {
            logger.info("Could not load properties from path:" + location + ", " + ex.getMessage());
        } finally {
            IOUtils.closeQuietly(is);
        }
    }
    return props;
}


}