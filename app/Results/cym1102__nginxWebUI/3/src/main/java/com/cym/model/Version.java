package com.cym.model;
 public class Version {

 private String version;

 private String docker;

 private String url;

 private String update;


public String getDocker(){
    return docker;
}


public String getVersion(){
    return version;
}


public String getUrl(){
    return url;
}


public void setVersion(String version){
    this.version = version;
}


public void setUpdate(String update){
    this.update = update;
}


public String getUpdate(){
    return update;
}


public void setDocker(String docker){
    this.docker = docker;
}


public void setUrl(String url){
    this.url = url;
}


}