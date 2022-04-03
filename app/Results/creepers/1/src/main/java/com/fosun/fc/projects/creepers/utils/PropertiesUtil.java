package com.fosun.fc.projects.creepers.utils;
 import org.springframework.util.StringUtils;
import com.fosun.fc.modules.utils.PropertiesLoader;
public class PropertiesUtil {

 private  PropertiesLoader applicationProperties;


public void loadApplication(){
    applicationProperties = new PropertiesLoader("classpath:/application.properties");
}


public String getApplicationValue(String key,String defaul){
    if (applicationProperties == null) {
        loadApplication();
    }
    Object value = applicationProperties.getProperty(key, "");
    if (StringUtils.isEmpty(value)) {
        System.err.println("cannot find the property of " + key + " in the file:" + " application.properties");
        return defaul;
    } else {
        return value.toString().trim();
    }
}


}