package com.lingxiang2014;
 import java.util.Date;
public class FileInfo {

 private  String name;

 private  String url;

 private  Boolean isDirectory;

 private  Long size;

 private  Date lastModified;


public void setName(String name){
    this.name = name;
}


public String getUrl(){
    return url;
}


public Long getSize(){
    return size;
}


public String getName(){
    return name;
}


public void setSize(Long size){
    this.size = size;
}


public Date getLastModified(){
    return lastModified;
}


public void setIsDirectory(Boolean isDirectory){
    this.isDirectory = isDirectory;
}


public Boolean getIsDirectory(){
    return isDirectory;
}


public void setLastModified(Date lastModified){
    this.lastModified = lastModified;
}


public void setUrl(String url){
    this.url = url;
}


}