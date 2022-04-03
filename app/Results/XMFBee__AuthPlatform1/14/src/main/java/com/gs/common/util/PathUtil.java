package com.gs.common.util;
 public class PathUtil {

 public  String WEB_INF;

 public  String CLASSPATH;


public String getClasspath(){
    return PathUtil.class.getResource("/").getPath();
}


public String getWEBINFPath(){
    String classPath = getClasspath();
    return classPath.substring(0, classPath.length() - ("classes".length() + 1));
}


public String getResourcePath(String configLocation){
    String resourcePath = null;
    if (configLocation.contains(CLASSPATH)) {
        resourcePath = getClasspath() + configLocation.substring(CLASSPATH.length());
    } else if (configLocation.contains(WEB_INF)) {
        resourcePath = getWEBINFPath() + configLocation.substring(WEB_INF.length());
    }
    return resourcePath;
}


}