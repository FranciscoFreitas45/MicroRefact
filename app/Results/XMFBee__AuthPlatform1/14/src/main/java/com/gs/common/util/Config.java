package com.gs.common.util;
 import java.io;
import java.util.Properties;
public class Config {

 public  Properties properties;

public Config() {
}
public long getLong(String key){
    return Long.valueOf(properties.getProperty(key));
}


public int getInt(String key){
    return Integer.valueOf(properties.getProperty(key));
}


public void build(String location){
    properties = new Properties();
    try {
        properties.load(new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(PathUtil.getResourcePath(location))))));
    } catch (IOException e) {
        e.printStackTrace();
    }
}


public double getDouble(String key){
    return Double.valueOf(properties.getProperty(key));
}


public String getString(String key){
    return properties.getProperty(key);
}


}