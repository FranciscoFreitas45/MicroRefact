package com.ukefu.util;
 public class BrowserClient {

 private  String useragent;

 private  String os;

 private  String browser;

 private  String version;

 private  String osversion;


public String getVersion(){
    return version;
}


public String getOs(){
    return os;
}


public void setOs(String os){
    this.os = os;
}


public String getUseragent(){
    return useragent;
}


public void setVersion(String version){
    this.version = version;
}


public void setBrowser(String browser){
    this.browser = browser;
}


public String getOsversion(){
    return osversion;
}


public void setOsversion(String osversion){
    this.osversion = osversion;
}


public void setUseragent(String useragent){
    this.useragent = useragent;
}


public String getBrowser(){
    return browser;
}


}