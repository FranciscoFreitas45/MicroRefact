package com.xwtec.xwserver.util;
 import java.io.UnsupportedEncodingException;
import java.util.Properties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import com.xwtec.xwserver.constant.ConstantKeys;
public class ProUtil extends PropertyPlaceholderConfigurer{

 private  Properties properties;


public String get(String key){
    String result;
    try {
        result = new String(properties.getProperty(key).getBytes(ConstantKeys.WebKey.CHARACTER_ENCODING_ISO_8859_1), ConstantKeys.WebKey.CHARACTER_ENCODING_UTF8);
    } catch (UnsupportedEncodingException e) {
        result = properties.getProperty(key);
    }
    return result;
}


public void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,Properties props){
    super.processProperties(beanFactoryToProcess, props);
    properties = props;
}


}