package com.gbcom.system.manager;
 public class ConfigManager {

 private  String siteName;

 private  String filePath;

 private  String baiduApiKey;

 private  String baiduSecretKey;

 private  String dbFilePath;

 private  String appFilePath;


public String getDbFilePath(){
    return this.dbFilePath;
}


public String getBaiduApiKey(){
    return baiduApiKey;
}


public void setAppFilePath(String appFilePath){
    this.appFilePath = appFilePath;
}


public String getFilePath(){
    return filePath;
}


public void setDbFilePath(String dbFilePath){
    this.dbFilePath = dbFilePath;
}


public void setSiteName(String siteName){
    this.siteName = siteName;
}


public void setFilePath(String filePath){
    this.filePath = filePath;
}


public void setBaiduApiKey(String baiduApiKey){
    this.baiduApiKey = baiduApiKey;
}


public void setBaiduSecretKey(String baiduSecretKey){
    this.baiduSecretKey = baiduSecretKey;
}


public String getAppFilePath(){
    return this.appFilePath;
}


public String getSiteName(){
    return siteName;
}


public String getBaiduSecretKey(){
    return baiduSecretKey;
}


}