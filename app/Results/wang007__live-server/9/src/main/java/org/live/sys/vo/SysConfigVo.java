package org.live.sys.vo;
 public class SysConfigVo {

 private  String systemTitle;

 private  int passwordRetryCount;

 private  int logLevel;


public String getSystemTitle(){
    return systemTitle;
}


public void setLogLevel(int logLevel){
    this.logLevel = logLevel;
}


public void setPasswordRetryCount(int passwordRetryCount){
    this.passwordRetryCount = passwordRetryCount;
}


public int getLogLevel(){
    return logLevel;
}


public int getPasswordRetryCount(){
    return passwordRetryCount;
}


public void setSystemTitle(String systemTitle){
    this.systemTitle = systemTitle;
}


}