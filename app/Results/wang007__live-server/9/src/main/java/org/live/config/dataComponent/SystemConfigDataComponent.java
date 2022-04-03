package org.live.config.dataComponent;
 import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
@Component
@ConfigurationProperties(prefix = "system")
@PropertySource("classpath:systemConfig.properties")
public class SystemConfigDataComponent {

 private  String title;

 private  String logLevel;

 private  int passwordRetryCount;

 private  String windowsUploadFileRootPath;

 private  String linuxUploadFileRootPath;

 private  String uploadFilePathPrefix;


public void setWindowsUploadFileRootPath(String windowsUploadFileRootPath){
    this.windowsUploadFileRootPath = windowsUploadFileRootPath;
}


public String getTitle(){
    return title;
}


public void setLogLevel(String logLevel){
    this.logLevel = logLevel;
}


public String getLogLevel(){
    return logLevel;
}


public void setPasswordRetryCount(int passwordRetryCount){
    this.passwordRetryCount = passwordRetryCount;
}


public String getWindowsUploadFileRootPath(){
    return windowsUploadFileRootPath;
}


public void setTitle(String title){
    this.title = title;
}


public int getPasswordRetryCount(){
    return passwordRetryCount;
}


public void setLinuxUploadFileRootPath(String linuxUploadFileRootPath){
    this.linuxUploadFileRootPath = linuxUploadFileRootPath;
}


public void setUploadFilePathPrefix(String uploadFilePathPrefix){
    this.uploadFilePathPrefix = uploadFilePathPrefix;
}


public String getLinuxUploadFileRootPath(){
    return linuxUploadFileRootPath;
}


public String getUploadFilePathPrefix(){
    return uploadFilePathPrefix;
}


}