package com.gbcom.update.client.xml;
 import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
@XStreamAlias("FileFilter")
public class FileFilter {

@XStreamAsAttribute
 private  String path;

@XStreamAsAttribute
 private  String exclude;

@XStreamAsAttribute
 private  String include;


public void setInclude(String include){
    this.include = include;
}


public String getInclude(){
    return include;
}


public void setExclude(String exclude){
    this.exclude = exclude;
}


public String getPath(){
    return path;
}


public void setPath(String path){
    this.path = path;
}


public String getExclude(){
    return exclude;
}


}