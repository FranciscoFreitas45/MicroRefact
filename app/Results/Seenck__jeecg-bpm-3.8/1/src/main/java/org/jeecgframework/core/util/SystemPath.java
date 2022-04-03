package org.jeecgframework.core.util;
 public class SystemPath {


public String getClassPath(){
    String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
    String temp = path.replaceFirst("file:/", "");
    String separator = System.getProperty("file.separator");
    String resultPath = temp.replaceAll("/", separator + separator);
    return resultPath;
}


public String getSysPath(){
    String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
    String temp = path.replaceFirst("file:/", "").replaceFirst("WEB-INF/classes/", "");
    String separator = System.getProperty("file.separator");
    String resultPath = temp.replaceAll("/", separator + separator);
    return resultPath;
}


public String getSystempPath(){
    return System.getProperty("java.io.tmpdir");
}


public String getSeparator(){
    return System.getProperty("file.separator");
}


public void main(String[] args){
    org.jeecgframework.core.util.LogUtil.info(getSysPath());
    org.jeecgframework.core.util.LogUtil.info(System.getProperty("java.io.tmpdir"));
    org.jeecgframework.core.util.LogUtil.info(getSeparator());
    org.jeecgframework.core.util.LogUtil.info(getClassPath());
}


}